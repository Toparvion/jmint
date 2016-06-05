package ru.ftc.upc.testing.dropper.lang;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.RuleNode;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaBaseVisitor;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaParser;

/**
 * @author Toparvion
 */
class GenericTypeSearchVisitor extends DroppingJavaBaseVisitor<ParserRuleContext> {

  @Override
  protected boolean shouldVisitNextChild(RuleNode node, ParserRuleContext currentResult) {
    return (currentResult == null);
  }

  @Override
  public ParserRuleContext visitUnannTypeVariable(DroppingJavaParser.UnannTypeVariableContext ctx) {
    return ctx;
  }

  @Override
  public ParserRuleContext visitTypeParameters(DroppingJavaParser.TypeParametersContext ctx) {
    return ctx;
  }
}
