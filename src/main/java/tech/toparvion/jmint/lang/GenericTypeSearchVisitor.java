package tech.toparvion.jmint.lang;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.RuleNode;
import tech.toparvion.jmint.lang.gen.DroppingJavaBaseVisitor;
import tech.toparvion.jmint.lang.gen.DroppingJavaParser;

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
