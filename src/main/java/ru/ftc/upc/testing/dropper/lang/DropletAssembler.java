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

import static java.util.regex.Matcher.quoteReplacement;
import static ru.ftc.upc.testing.dropper.Cutpoint.IGNORE;

/**
 * The main class responsible for extracting valuable data from droplet files. Built upon ANTLR parse tree event
 * listener.
 * @author Toparvion
 */
public class DropletAssembler extends DroppingJavaBaseListener {

  /**
   * Mapping between all target classes and lists of their methods. These methods are targets for byte
   * code instrumenting.
   */
  private final TargetsMap targetsMap = new TargetsMap();

  /**
   * Mapping between simple type names (e.g. {@code Droplet}) and their FQ prefixes (e.g.
   * {@code ru.ftc.upc.testing.dropper}).
   * The mapping is built upon the import statements at the header of compilation unit. <br/>
   * It also includes single static imports (e.g. {@code import static java.lang.Integer.MAX_VALUE}) and does not
   * include any type imports on demand (neither {@code java.util.*} nor {@code import static java.lang.Double.*}).
   */
  /*private - for unit tests*/ final Map<String, String> singleImportsMap = new LinkedHashMap<String, String>(10);

  /**
   * Package prefix for the target type(s), e.g. "tech.toparvion.dropper.lang.droplets."
   */
  private String packageDeclaration = "";
  /**
   * A set of non-static imports on demand. Includes package names only (without {@code .* chars}).<br/>
   * The set is used later at the instrumentation phase for resolving FQ-names of methods' formal parameters as well
   * as for importing the packages into Javassist compiler.
   */
  private final Set<String> importsOnDemand = new LinkedHashSet<String>();
  /**
   * Internal stack aimed to support depth tracking during parse tree traversing.
   */
  private final Deque<String> classNameStack = new LinkedList<String>();
  /**
   * A reference to source token stream to access hidden channels.
   */
  private final BufferedTokenStream tokenStream;

  public DropletAssembler(BufferedTokenStream tokenStream) {
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
    singleImportsMap.put(typeName.Identifier().getText(), typeName.packageOrTypeName().getText());
  }

  @Override
  public void enterSingleStaticImportDeclaration(DroppingJavaParser.SingleStaticImportDeclarationContext ctx) {
    DroppingJavaParser.TypeNameContext typeName = ctx.typeName();
    singleImportsMap.put(ctx.Identifier().getText(), typeName.getText());
  }

  @Override
  public void enterTypeImportOnDemandDeclaration(DroppingJavaParser.TypeImportOnDemandDeclarationContext ctx) {
    importsOnDemand.add(ctx.packageOrTypeName().getText());
  }

  @Override
  public void enterStaticImportOnDemandDeclaration(DroppingJavaParser.StaticImportOnDemandDeclarationContext ctx) {
    /**
     * Static type imports on demand are not supported in this version because they introduce quite complicated
     * ambiguity. In order not to mute their existence, we prefer to explicitly inform the user about them with the
     * help of warning.
     */
    Token offendingToken = ctx.getToken(DroppingJavaParser.IMPORT, 0).getSymbol();
    String offendingImportString = String.format("import static %s.*;", ctx.typeName().getText());
    System.out.printf("WARNING: Static imports on demand are not " +
            "supported. If they are used in not ignored methods please replace " +
            "them with a set of single static imports.\nLine %d:%d - %s\n",
            offendingToken.getLine(), offendingToken.getCharPositionInLine(), offendingImportString);
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

  @Override
  public void enterConstructorDeclarator(DroppingJavaParser.ConstructorDeclaratorContext ctx) {
    // constructors are treated much like any other methods but still some grammar differences are taken in count
    String constructorName = omitDropletSuffix(ctx.simpleTypeName().getText());
    // let's start from extracting the cutpoint from preceding javadoc comment, if any
    Cutpoint cutpoint = Cutpoint.getByNameSafe(extractCutpointString(ctx));
    if (!IGNORE.equals(cutpoint)) {
      // at this moment the underlying system doesn't support parametrized types in methods so we have to inform the user
      checkForParametrizedTypesPresence(ctx);             // throws DropletFormatException if type params found
    }
    // as we consider constructor much like methods, let's firstly initialize it
    TargetMethod constructor = new TargetMethod(constructorName, cutpoint, importsOnDemand);

    // There is no result type for constructors so we pass setting Method's result field. Proceed to formal parameters.
    DroppingJavaParser.FormalParameterListContext anyParams = ctx.formalParameterList();
    storeMethodParams(constructor, anyParams);

    // now that we know everything about the constructor we can store it into targets map
    targetsMap.put(composeCurrentKey(), constructor);
  }

  @Override
  public void enterMethodHeader(DroppingJavaParser.MethodHeaderContext ctx) {
    DroppingJavaParser.MethodDeclaratorContext declarator = ctx.methodDeclarator();
    String methodName = declarator.Identifier().getText();

    // let's start from extracting the cutpoint from preceding javadoc comment, if any
    Cutpoint cutpoint = Cutpoint.getByNameSafe(extractCutpointString(ctx));
    if (!IGNORE.equals(cutpoint)) {
      // at this moment the underlying system doesn't support parametrized types in methods so we have to inform the user
      checkForParametrizedTypesPresence(ctx);             // throws DropletFormatException if type params found
    }
    // first of all let's initialize this method itself
    TargetMethod method = new TargetMethod(methodName, cutpoint, importsOnDemand);

    // store method's result type name
    DroppingJavaParser.ResultContext result = ctx.result();
    String resultType = extractPureTypeName(result);
    if (!"void".equals(resultType)) {           // avoid excess work
      resultType = resolveMethodArgumentType(resultType);
    }
    method.setResultType(resultType);

    // it's time to store method parameters, if any
    DroppingJavaParser.FormalParameterListContext anyParams = declarator.formalParameterList();
    storeMethodParams(method, anyParams);

    // finally we can store the method in targets map
    targetsMap.put(composeCurrentKey(), method);
  }

  @Override
  public void enterBlockStatements(DroppingJavaParser.BlockStatementsContext ctx) {
    DroppingJavaVisitor<String> visitor = new BodyComposingVisitor();

    String bodyText = visitor.visit(ctx);
    if (bodyText == null)       // there is no profit in dealing with absent body text anymore
      return;
    bodyText = makeTypeNamesFullyQualified(bodyText);
    TargetMethod currentMethod = targetsMap.get(composeCurrentKey()).getLast();
    bodyText = obfuscateParameterReferences(bodyText, currentMethod.getFormalParams());
    currentMethod.setText("{ " + bodyText + " }");
  }
  //endregion

  //region Auxiliary methods of the assembler
  /**
   * Extracts formal parameters types and names and stores them into the given target method.
   * @param method target method to store extracted parameters into
   * @param anyParams parse rule context of formal parameters list; may be {@code null}
   */
  private void storeMethodParams(TargetMethod method, DroppingJavaParser.FormalParameterListContext anyParams) {
    if (anyParams == null) return;
    String paramType;         // for the sake of brevity we use term 'paramType' instead of 'paramTypeName'
    String paramName;
    // first we check for any normal formal params
    DroppingJavaParser.FormalParametersContext formalParams = anyParams.formalParameters();
    if (formalParams != null) {
      for (DroppingJavaParser.FormalParameterContext param : formalParams.formalParameter()) {
        paramType = extractPureTypeName(param.unannType());
        paramType = resolveMethodArgumentType(paramType);
        paramName = param.variableDeclaratorId().Identifier().getText();
        if (param.variableDeclaratorId().dims() != null) {          // dimensions might be specified on param name
          paramType = paramType + param.variableDeclaratorId().dims().getText();
        }
        method.getFormalParams().add(new Argument(paramType, paramName));
      }
    }
    // now we check for the last formal param which can be ellipsis one
    DroppingJavaParser.LastFormalParameterContext lastFormalParam = anyParams.lastFormalParameter();
    if (lastFormalParam != null) {
      DroppingJavaParser.FormalParameterContext formalParam = lastFormalParam.formalParameter();
      if (formalParam != null) {
        paramType = extractPureTypeName(formalParam.unannType());
        paramName = formalParam.variableDeclaratorId().Identifier().getText();
        if (formalParam.variableDeclaratorId().dims() != null) {          // dimensions might be specified on param name
          paramType = paramType + formalParam.variableDeclaratorId().dims().getText();
        }
      } else {
        paramType = extractPureTypeName(lastFormalParam.unannType());
        paramName = lastFormalParam.variableDeclaratorId().Identifier().getText();
        if (lastFormalParam.variableDeclaratorId().dims() != null) {      // dimensions might be specified on param name
          paramType = paramType + lastFormalParam.variableDeclaratorId().dims().getText();
        }
      }
      paramType = resolveMethodArgumentType(paramType);
      method.getFormalParams().add(new Argument(paramType, paramName));
    }
  }

  /**
   * Applies 2 modifications to given type name: (1) prepends it with package path according to
   * {@link #singleImportsMap} of this droplet; (2) converts it to {@link ClassLoader binary} name format, i.e. replaces
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
            ? argumentType.replaceAll("\\.", quoteReplacement("$"))
            : argumentType;
    // then we can search for corresponding import
    String fqPrefix = singleImportsMap.get(outerType);
    return (fqPrefix == null)
            ? binaryName
            : (fqPrefix + "." + binaryName);
  }

  /**
   * Finds the latest entry of fully-qualified type name, e.g. {@code Set} from {@code java.util.Set<String>}.
   * @param typeCtx type rule context to extract the name from
   * @return simple name of the type (with package and type parameters cleaned off)
   */
  private String extractPureTypeName(RuleContext typeCtx) {
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
      // Javadoc comments are neighbors to classBodyDeclaration, not the methodHeader so we have to find this anchor.
      ParserRuleContext classBodyDeclaration = findJavadocAnchor(ctx);
      if (classBodyDeclaration == null)       // this means the anchor was not found
        return null;
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
      parser.removeErrorListeners(); // javadoc is not strict format so we don't want to bother of its correctness here
      ParseTree tree = parser.documentation();

      // then find the actual needed value with dedicated listener
      CutpointAssembler assembler = new CutpointAssembler();
      ParseTreeWalker walker = new ParseTreeWalker();
      walker.walk(assembler, tree);

      // and return found value (which may eventually be null)
      return assembler.getTagValue();

    } catch (Exception e) {   // we can't be sure about anything in comments so that we need a defense line
      System.out.printf("Couldn't extract cutpoint from javadoc comments: '%s'. Falling back to default.\n", e.getMessage());
      return null;
    }
  }

  /**
   * @return an actual key for targets map (composed basing on current state of the {@linkplain #classNameStack})
   */
  private String composeCurrentKey() {
    StringBuilder sb = new StringBuilder(packageDeclaration.replaceAll("\\.", "/"));
    boolean first = true;
    for (Iterator<String> iterator = classNameStack.descendingIterator(); iterator.hasNext(); ) {
      String className = iterator.next();
      if (!first) {
        sb.append("$");
      } else {
        first = false;
      }
      className = omitDropletSuffix(className);
      sb.append(className);
    }

    return sb.toString();
  }

  /**
   * @param className original droplet class name
   * @return the {@code className} without last <i>Droplet</i> or <i>_Droplet</i> suffix
   */
  private String omitDropletSuffix(String className) {
    return className.replaceFirst("(?i)_?Droplet$", "");
  }

  /**
   * Replaces all the type names in {@code bodyText} with their fully qualified equivalents according to
   * {@link #singleImportsMap} of the droplet. Skips names that are already fully qualified.
   * Does nothing if a type has no import (leaving it for resolving during the instrumentation).
   * @param bodyText text of method's body being processed
   * @return the {@code bodyText} with fully qualified type names
   */
  private String makeTypeNamesFullyQualified(String bodyText) {
    if (bodyText == null || bodyText.isEmpty())
      return bodyText;
    String processedText = bodyText;
    for (Map.Entry<String, String> importEntry : singleImportsMap.entrySet()) {
      String simpleName = importEntry.getKey();
      String fqName = importEntry.getValue() + "." + simpleName;
      Pattern pattern = Pattern.compile("(\\b)" + simpleName + "(\\b)");
      Matcher matcher = pattern.matcher(processedText);
      StringBuffer sb = new StringBuffer(bodyText.length()+30);
      while (matcher.find()) {
        // by the following check we exclude references to already qualified types (both fully and inner)
        if (!isNameAlreadyQualified(processedText, matcher.start())) {
          matcher.appendReplacement(sb, "$1" + fqName + "$2");
        }
      }
      matcher.appendTail(sb);
      processedText = sb.toString();
    }
    return processedText;
  }

  /**
   * Detects whether the type name on position denoted by {@code typeNameStartPosition} is qualified in any way. This
   * includes both cases when the name is fully qualified (e.g. {@code java.util.Map}) and when it is bounded within a
   * containing type (e.g. {@code KeyStore.Builder}). In the latter case the resolving will be applied to containing
   * type ({@code KeyStore}), not the contained one ({@code Builder}).
   */
  private boolean isNameAlreadyQualified(String wholeText, int typeNameStartPosition) {
    int precedingSymbolStartPosition = Math.max(0, (typeNameStartPosition-2));
    return ". ".equals(wholeText.substring(precedingSymbolStartPosition, typeNameStartPosition));
  }

  /**
   * Replaces formal parameters references with symbolic equivalents according to Javassist notation (i.e. replaces
   * explicit names with ordinal links, for example {@code callMethod(someParam)} with {@code callMethod($1)}).
   * @param bodyText original method's body text
   * @param formalParams ordered list of method's formal parameters
   * @return reformatted body text (may be the same as original)
   */
  private String obfuscateParameterReferences(String bodyText, List<Argument> formalParams) {
    if (bodyText == null || bodyText.isEmpty())
      return bodyText;
    String processedText = bodyText;
    for (int i = 1; i <= formalParams.size(); i++) {
      String paramName = formalParams.get(i-1).getName();
      Pattern pattern = Pattern.compile("(\\b)" + paramName + "(\\b)");
      Matcher matcher = pattern.matcher(processedText);
      StringBuffer sb = new StringBuffer(bodyText.length());
      while (matcher.find()) {
        // by the following check we exclude ambiguous references to some field with a name equal to current param
        if (!isNameAlreadyQualified(bodyText, matcher.start())) {
          matcher.appendReplacement(sb, "$1" + quoteReplacement("$"+i) + "$2");
        }
      }
      matcher.appendTail(sb);
      processedText = sb.toString();
    }
    return processedText;
  }

  private DroppingJavaParser.ClassBodyDeclarationContext findJavadocAnchor(ParserRuleContext start) {
    ParserRuleContext next = start;
    while (!(next instanceof DroppingJavaParser.ClassBodyDeclarationContext) && next != null) {
      next = next.getParent();
    }
    return (DroppingJavaParser.ClassBodyDeclarationContext) next;
  }

  /**
   * Checks for presence of parametrized types in given rule context and throws DropletFormatException if finds.
   * Due to error in the original grammar this method doesn't detects generic types of formal parameters though the
   * visitor has special handling for it.
   * @param ctx parser rule context
   */
  private void checkForParametrizedTypesPresence(ParserRuleContext ctx) {
    DroppingJavaVisitor<ParserRuleContext> visitor = new GenericTypeSearchVisitor();
    ParserRuleContext token = visitor.visit(ctx);
    if (token != null) {
      String message = String.format("Line %d:%d - %s: %s", token.getStart().getLine(),
              token.getStart().getCharPositionInLine(),
              "Methods with parametrized types are not supported", new BodyComposingVisitor().visit(token));
      throw new DropletFormatException(message);
    }
  }
  //endregion

  //region Getters
  public TargetsMap getTargetsMap() {
    return targetsMap;
  }

  //endregion

}
