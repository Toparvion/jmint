package ru.ftc.upc.testing.dropper.lang;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaBaseListener;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaBaseVisitor;
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

  private Deque<String> classStack = new LinkedList<String>();
  private TargetsMap targetsMap = new TargetsMap();

  @Override
  public void enterNormalClassDeclaration(DroppingJavaParser.NormalClassDeclarationContext ctx) {
    classStack.push(ctx.Identifier().getText());
  }

  @Override
  public void exitNormalClassDeclaration(DroppingJavaParser.NormalClassDeclarationContext ctx) {
    classStack.pop();
  }

  @Override
  public void enterMethodHeader(DroppingJavaParser.MethodHeaderContext ctx) {
    // first of all let's save this method itself
    String key = composeCurrentKey();
    DroppingJavaParser.MethodDeclaratorContext declarator = ctx.methodDeclarator();
    TargetMethod method = new TargetMethod(declarator.Identifier().getText());
    targetsMap.appendMethod(key, method);

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

  /**
   * @return a key for target class map (basing on current state of the stack)
   */
  private String composeCurrentKey() {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (Iterator<String> iterator = classStack.descendingIterator(); iterator.hasNext(); ) {
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

  private String getSpacedText(ParserRuleContext ctx) {
    int childCount = ctx.getChildCount();
    if (childCount == 0) {
      return "";
    }

    StringBuilder sb = new StringBuilder(ctx.getChild(0).getText());
    for (int i=1; i<childCount; i++) {
      sb.append(" ").append(ctx.getChild(i));
    }

    return sb.toString();
  }

  private String getPureTypeName(RuleContext typeCtx) {
    DroppingJavaVisitor<String> visitor = new FirstIdTreeVisitor();
    return visitor.visit(typeCtx);
  }

  /**
   * Simple visitor that reaches the very first terminal node (starting from the one specified in
   * {@link #visitTerminal} method) and returns the node's text value.
   */
  private static class FirstIdTreeVisitor extends DroppingJavaBaseVisitor<String> {
    @Override
    public String visitTerminal(TerminalNode node) {
      return node.getText();
    }

    @Override
    protected boolean shouldVisitNextChild(RuleNode node, String currentResult) {
      return (currentResult == null);
    }
  }
}
