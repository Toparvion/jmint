package ru.ftc.upc.testing.dropper.lang;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.ftc.upc.testing.dropper.Cutpoint;
import ru.ftc.upc.testing.dropper.lang.gen.*;
import ru.ftc.upc.testing.dropper.model.Argument;
import ru.ftc.upc.testing.dropper.model.TargetMethod;
import ru.ftc.upc.testing.dropper.model.TargetsMap;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main class responsible for extracting valuable data from droplet files. Built upon ANTLR parse tree event
 * listener.
 * @author Toparvion
 */
class DropletAssembler extends DroppingJavaBaseListener {

  /**
   * Mapping between simple type names (e.g. "Droplet") and their FQ prefixes (e.g. "ru.ftc.upc.testing.dropper").
   * The mapping is built upon the import statements at the header of compilation unit and does not include any type
   * imports on demand.
   */
  private final Map<String, String> importsMap = new LinkedHashMap<String, String>(10);
  /**
   * Mapping between all target classes and lists of their methods. These methods are targets for byte
   * code instrumenting.
   */
  private final TargetsMap targetsMap = new TargetsMap();

  /**
   * Package prefix for the target type(s), e.g. "tech.toparvion.dropper.lang.droplets."
   */
  private String packageDeclaration = "";
  /**
   * Internal stack aimed to support depth tracking during parse tree traversing.
   */
  private final Deque<String> classNameStack = new LinkedList<String>();
  /**
   * A reference to source token stream to access hidden channels.
   */
  private final BufferedTokenStream tokenStream;

  DropletAssembler(BufferedTokenStream tokenStream) {
    this.tokenStream = tokenStream;
  }

  //region Parse tree event listener methods
  @Override
  public void enterPackageDeclaration(DroppingJavaParser.PackageDeclarationContext ctx) {
    StringBuilder sb = new StringBuilder();
    for (TerminalNode idNode : ctx.Identifier()) {
      sb.append(idNode.getText()).append(".");
    }
    this.packageDeclaration = sb.toString();
  }

  @Override
  public void enterSingleTypeImportDeclaration(DroppingJavaParser.SingleTypeImportDeclarationContext ctx) {
    DroppingJavaParser.TypeNameContext typeName = ctx.typeName();
    if (typeName.packageOrTypeName() == null) {
      // in this case there is no profit in mapping a type to its import prefix so that we just ignore it
      return;
    }
    importsMap.put(typeName.Identifier().getText(), typeName.packageOrTypeName().getText());
  }

  /**
   * Type imports on demand are not supported in this version because they introduce quite complicated ambiguity. In
   * order not to mute their existence we prefer to explicitly inform the user about them with the help of exception.
   */
  @Override
  public void enterTypeImportOnDemandDeclaration(DroppingJavaParser.TypeImportOnDemandDeclarationContext ctx) {
    Token offendingToken = ctx.getToken(DroppingJavaParser.IMPORT, 0).getSymbol();
    String offendingImportString = String.format("import %s.*;", ctx.packageOrTypeName().getText());
    throw new DropletFormatException(String.format("Line %d:%d. Type imports on demand are not supported: '%s'. " +
                    "Please replace it with a set of single type imports.",
            offendingToken.getLine(), offendingToken.getCharPositionInLine(), offendingImportString));
  }

  @Override
  public void enterNormalClassDeclaration(DroppingJavaParser.NormalClassDeclarationContext ctx) {
    classNameStack.push(ctx.Identifier().getText());
  }
  @Override
  public void exitNormalClassDeclaration(DroppingJavaParser.NormalClassDeclarationContext ctx) {
    classNameStack.pop();
  }

  @Override
  public void enterEnumDeclaration(DroppingJavaParser.EnumDeclarationContext ctx) {
    classNameStack.push(ctx.Identifier().getText());
  }
  @Override
  public void exitEnumDeclaration(DroppingJavaParser.EnumDeclarationContext ctx) {
    classNameStack.pop();
  }

  @Override
  public void enterNormalInterfaceDeclaration(DroppingJavaParser.NormalInterfaceDeclarationContext ctx) {
    classNameStack.push(ctx.Identifier().getText());
  }
  @Override
  public void exitNormalInterfaceDeclaration(DroppingJavaParser.NormalInterfaceDeclarationContext ctx) {
    classNameStack.pop();
  }

  /**
   * We treat constructors much like any other methods but still take in account some grammar differences.
   */
  @Override
  public void enterConstructorDeclarator(DroppingJavaParser.ConstructorDeclaratorContext ctx) {
    // as we consider constructor as methods, let's firstly save it
    String key = composeCurrentKey();
    TargetMethod method = new TargetMethod(ctx.simpleTypeName().getText());
    targetsMap.put(key, method);

    // There is no result type for constructors so we pass setting Method's result field. Proceed to formal parameters.
    DroppingJavaParser.FormalParameterListContext anyParams = ctx.formalParameterList();
    storeMethodParams(method, anyParams);
  }

  @Override
  public void enterMethodHeader(DroppingJavaParser.MethodHeaderContext ctx) {
    // first of all let's save this method itself
    String key = composeCurrentKey();
    DroppingJavaParser.MethodDeclaratorContext declarator = ctx.methodDeclarator();
    TargetMethod method = new TargetMethod(declarator.Identifier().getText());
    targetsMap.put(key, method);

    // store method's result type name
    DroppingJavaParser.ResultContext result = ctx.result();
    String resultType = getPureTypeName(result);
    if (!"void".equals(resultType)) {           // avoid excess work
      resultType = resolveMethodArgumentType(resultType);
    }
    method.setResultType(resultType);

    // it's time to store method parameters, if any
    DroppingJavaParser.FormalParameterListContext anyParams = declarator.formalParameterList();
    storeMethodParams(method, anyParams);

    // and finally let's extract the cutpoint from preceding javadoc comment, if any
    String cutpointStr = extractCutpointString(ctx);
    if (cutpointStr != null) {
      method.setCutpoint(Cutpoint.valueOf(cutpointStr.toUpperCase()));
    }
  }

  @Override
  public void enterBlockStatements(DroppingJavaParser.BlockStatementsContext ctx) {
    DroppingJavaVisitor<String> visitor = new BodyComposingVisitor();

    String bodyText = visitor.visit(ctx);
    bodyText = makeTypeNamesFullyQualified(bodyText);
    TargetMethod currentMethod = targetsMap.get(composeCurrentKey()).getLast();
    currentMethod.setText(bodyText);
  }
  //endregion

  //region Auxiliary methods of the assembler
  /**
   * Extracts formal parameters types and names and stores them into the given target method.
   * @param method target method to store extracted parameters into
   * @param anyParams  parse rule context of formal parameters list; may be {@code null}
   */
  private void storeMethodParams(TargetMethod method, DroppingJavaParser.FormalParameterListContext anyParams) {
    if (anyParams == null) return;
    String paramType;         // for the sake of brevity we use term 'paramType' instead of 'paramTypeName'
    String paramName;
    // first we check for any normal formal params
    DroppingJavaParser.FormalParametersContext formalParams = anyParams.formalParameters();
    if (formalParams != null) {
      for (DroppingJavaParser.FormalParameterContext param : formalParams.formalParameter()) {
        paramType = getPureTypeName(param.unannType());
        paramType = resolveMethodArgumentType(paramType);
        paramName = param.variableDeclaratorId().Identifier().getText();
        method.getFormalParams().add(new Argument(paramType, paramName));
      }
    }
    // now we check for the last formal param which can be ellipsis one
    DroppingJavaParser.LastFormalParameterContext lastFormalParam = anyParams.lastFormalParameter();
    if (lastFormalParam != null) {
      DroppingJavaParser.FormalParameterContext formalParam = lastFormalParam.formalParameter();
      if (formalParam != null) {
        paramType = getPureTypeName(formalParam.unannType());
        paramName = formalParam.variableDeclaratorId().Identifier().getText();
      } else {
        paramType = getPureTypeName(lastFormalParam.unannType());
        paramName = lastFormalParam.variableDeclaratorId().Identifier().getText();
      }
      paramType = resolveMethodArgumentType(paramType);
      method.getFormalParams().add(new Argument(paramType, paramName));
    }
  }

  /**
   * Applies 2 modifications to given type name: (1) prepends it with package path according to
   * {@link #importsMap} of this droplet; (2) converts it to {@link ClassLoader binary} name format, i.e. replaces
   * dots in non-package part with dollar signs (e.g. turns {@code Map.Entry} to {@code Map$Entry}).
   * @param argumentType  original parameter or result type name as it was extracted from droplet parse tree
   * @return resolved name (may appear the same)
   */
  private String resolveMethodArgumentType(String argumentType) {
    if (argumentType == null || argumentType.isEmpty())
      return argumentType;
    // first we need to cut off any references to inner types
    String[] typeEntries = argumentType.split("\\.");
    String outerType = typeEntries[0];
    // during resolving we must make the type name binary compatible (see java.lang.ClassLoader#name)
    String binaryName = typeEntries.length > 1
            ? argumentType.replaceAll("\\.", Matcher.quoteReplacement("$"))
            : argumentType;
    // then we can search for corresponding import
    String fqPrefix = importsMap.get(outerType);
    return (fqPrefix == null)
            ? binaryName
            : (fqPrefix + "." + binaryName);
  }

  /**
   * Finds the latest entry of fully-qualified type name, e.g. {@code Set} from {@code java.util.Set<String>}.
   * @param typeCtx type rule context to extract the name from
   * @return simple name of the type (with package and type parameters cleaned off)
   */
  private String getPureTypeName(RuleContext typeCtx) {
    DroppingJavaVisitor<String> visitor = new PureTypeNameComposingVisitor();
    return visitor.visit(typeCtx);
  }

  /**
   * Tries to extract string representation of cutpoint from the preceding javadoc comment (if any).
   * @param ctx rule context of the node which the javadoc is relative to
   * @return string form of cutpoint or {@code null} if it cannot be extracted for any reason
   */
  private String extractCutpointString(ParserRuleContext ctx) {
    try {
      /* Javadoc comments are neighbors to classBodyDeclaration, not the methodHeader so we have to find the ancestor.*/
      ParserRuleContext classBodyDeclaration = ctx
              .getParent()    // methodDeclaration
              .getParent()    // classMemberDeclaration
              .getParent();   // classBodyDeclaration
      Token startToken = classBodyDeclaration.getStart();
      int startITokenIndex = startToken.getTokenIndex();
      // try to find out if there are any comments left to classBodyDeclaration
      List<Token> hiddenTokens = tokenStream.getHiddenTokensToLeft(startITokenIndex, DroppingJavaLexer.BLOCK_COMMENTS);
      if (hiddenTokens == null || hiddenTokens.isEmpty())
        return null;
      Token hiddenToken = hiddenTokens.get(0);
      if (hiddenToken == null)
        return null;

      // now that we've found one, let's recognize it with separate lexer/parser
      ANTLRInputStream inputStream = new ANTLRInputStream(hiddenToken.getText());
      JavadocLexer lexer = new JavadocLexer(inputStream);
      TokenStream tokenStream = new CommonTokenStream(lexer);
      JavadocParser parser = new JavadocParser(tokenStream);
      ParseTree tree = parser.documentation();

      // then find the actual needed value with dedicated listener
      CutpointAssembler assembler = new CutpointAssembler();
      ParseTreeWalker walker = new ParseTreeWalker();
      walker.walk(assembler, tree);

      // and return found value (which eventually may be null)
      return assembler.getTagValue();

    } catch (Exception e) {   // we can't be sure about anything in comments so that we need a defense line
      System.out.printf("Couldn't extract cutpoint from javadoc comments: '%s'. Falling back to default.", e.getMessage());
      return null;
    }
  }

  /**
   * @return an actual key for targets map (composed basing on current state of the {@linkplain #classNameStack})
   */
  private String composeCurrentKey() {
    StringBuilder sb = new StringBuilder(packageDeclaration);
    boolean first = true;
    for (Iterator<String> iterator = classNameStack.descendingIterator(); iterator.hasNext(); ) {
      String className = iterator.next();
      if (!first) {
        sb.append("$");
      } else {
        first = false;
      }
      sb.append(className);
    }

    return sb.toString();
  }

  /**
   * Replaces all the type names in {@code bodyText} with their fully qualified equivalents according to
   * {@link #importsMap} of the droplet. Skips names that are already fully qualified.
   * Does nothing if some type has no import.
   * @param bodyText text of method's body being processed
   * @return the {@code bodyText} with fully qualified type names
   */
  private String makeTypeNamesFullyQualified(String bodyText) {
    if (bodyText == null || bodyText.isEmpty())
      return bodyText;
    String processedText = bodyText;
    for (Map.Entry<String, String> importEntry : importsMap.entrySet()) {
      String simpleName = importEntry.getKey();
      String fqName = importEntry.getValue() + "." + simpleName;
      Pattern pattern = Pattern.compile("(\\b)" + simpleName + "(\\b)");
      Matcher matcher = pattern.matcher(processedText);
      StringBuffer sb = new StringBuffer(bodyText.length()+30);
      while (matcher.find()) {
        if (!isNameAlreadyQualified(processedText, matcher.start(2), fqName)) {
          matcher.appendReplacement(sb, "$1" + fqName + "$2");
        }
      }
      matcher.appendTail(sb);
      processedText = sb.toString();
    }
    return processedText;
  }

  /**
   * Detects whether type name on position denoted by {@code typeNameEndPosition} is fully qualified.
   */
  private boolean isNameAlreadyQualified(String wholeText, int typeNameEndPosition, String typeFqName) {
    String sparseFqName = typeFqName.replaceAll("\\.", " . ");
    int precursorStartPosition = Math.max(0, (typeNameEndPosition - sparseFqName.length()));
    String precursor = wholeText.substring(precursorStartPosition, typeNameEndPosition);
    return sparseFqName.equals(precursor);
  }
  //endregion

  //region Getters
  TargetsMap getTargetsMap() {
    return targetsMap;
  }

  Map<String, String> getImportsMap() {
    return importsMap;
  }
  //endregion

}
