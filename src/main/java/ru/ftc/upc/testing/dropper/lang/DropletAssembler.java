package ru.ftc.upc.testing.dropper.lang;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaBaseListener;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaParser;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaVisitor;
import ru.ftc.upc.testing.dropper.model.Argument;
import ru.ftc.upc.testing.dropper.model.TargetMethod;

import java.util.*;

/**
 * @author Toparvion
 */
class DropletAssembler extends DroppingJavaBaseListener {

  /**
   * Mapping between simple type names (e.g. "Droplet") and their FQ prefixes (e.g. "ru.ftc.upc.testing.dropper").
   * The mapping is built from the import statements at the header of compilation unit and does not include any type
   * import on demand.
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
   * Internal data structure aimed to support depth tracking during parse tree traversing.
   */
  private final Deque<String> classNameStack = new LinkedList<String>();

  @Override
  public void enterPackageDeclaration(DroppingJavaParser.PackageDeclarationContext ctx) {
    StringBuilder sb = new StringBuilder();
    for (TerminalNode idNode : ctx.Identifier()) {
      sb.append(idNode.getText()).append(".");
    }
    this.packageDeclaration = sb.toString();
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
    method.setResultType(resultType);

    // it's time to store method parameters, if any
    DroppingJavaParser.FormalParameterListContext anyParams = declarator.formalParameterList();
    storeMethodParams(method, anyParams);
  }

  @Override
  public void enterBlockStatements(DroppingJavaParser.BlockStatementsContext ctx) {
    DroppingJavaVisitor<String> visitor = new BodyAssembleTreeVisitor();

    String bodyText = visitor.visit(ctx);
    TargetMethod currentMethod = targetsMap.get(composeCurrentKey()).getLast();
    currentMethod.setText(bodyText);
  }

  private void storeMethodParams(TargetMethod method, DroppingJavaParser.FormalParameterListContext anyParams) {
    if (anyParams == null) return;
    String paramType;
    String paramName;
    // first we check for any normal formal params
    DroppingJavaParser.FormalParametersContext formalParams = anyParams.formalParameters();
    if (formalParams != null) {
      for (DroppingJavaParser.FormalParameterContext param : formalParams.formalParameter()) {
        paramType = getPureTypeName(param.unannType());
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
      method.getFormalParams().add(new Argument(paramType, paramName));
    }
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

  /**
   * @return a key for targets map (composed basing on current state of the stack)
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

  TargetsMap getTargetsMap() {
    return targetsMap;
  }

  Map<String, String> getImportsMap() {
    return importsMap;
  }

  private String getPureTypeName(RuleContext typeCtx) {
    DroppingJavaVisitor<String> visitor = new PureTypeNameComposingVisitor();
    return visitor.visit(typeCtx);
  }

}
