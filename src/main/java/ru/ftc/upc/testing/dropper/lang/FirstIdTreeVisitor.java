package ru.ftc.upc.testing.dropper.lang;

import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaBaseVisitor;

/**
 * Simple visitor that reaches the very first terminal node (starting from the one specified in
 * {@link #visitTerminal} method) and returns the node's text value.
 */
class FirstIdTreeVisitor extends DroppingJavaBaseVisitor<String> {

  @Override
  public String visitTerminal(TerminalNode node) {
    return node.getText();
  }

  @Override
  protected boolean shouldVisitNextChild(RuleNode node, String currentResult) {
    return (currentResult == null);
  }
}
