package ru.ftc.upc.testing.dropper.lang;

import org.antlr.v4.runtime.RuleContext;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaBaseListener;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaParser;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaVisitor;
import ru.ftc.upc.testing.dropper.model.Argument;
import ru.ftc.upc.testing.dropper.model.TargetMethod;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Toparvion
 */
public class DropperTreeListener extends DroppingJavaBaseListener {

  private Deque<String> classNameStack = new LinkedList<String>();
  private TargetsMap targetsMap = new TargetsMap();

  @Override
  public void enterNormalClassDeclaration(DroppingJavaParser.NormalClassDeclarationContext ctx) {
    classNameStack.push(ctx.Identifier().getText());
  }

  @Override
  public void exitNormalClassDeclaration(DroppingJavaParser.NormalClassDeclarationContext ctx) {
    classNameStack.pop();
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
  public void enterBlockStatements(DroppingJavaParser.BlockStatementsContext ctx) {
    DroppingJavaVisitor<String> visitor = new BodyAssembleTreeVisitor();

    String bodyText = visitor.visit(ctx);
    TargetMethod currentMethod = targetsMap.get(composeCurrentKey()).getLast();
    currentMethod.setText(bodyText);
  }

  /**
   * @return a key for targets map (composed basing on current state of the stack)
   */
  private String composeCurrentKey() {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (Iterator<String> iterator = classNameStack.descendingIterator(); iterator.hasNext(); ) {
      String className = iterator.next();
      if (!first) {
        sb.append(".");
      } else {
        first = false;
      }
      sb.append(className);
    }

    return sb.toString();
  }

  public TargetsMap getTargetsMap() {
    return targetsMap;
  }

  private String getPureTypeName(RuleContext typeCtx) {
    DroppingJavaVisitor<String> visitor = new FirstIdTreeVisitor();
    return visitor.visit(typeCtx);
  }

}
