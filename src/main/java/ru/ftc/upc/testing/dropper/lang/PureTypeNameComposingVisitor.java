package ru.ftc.upc.testing.dropper.lang;

import org.antlr.v4.runtime.tree.TerminalNode;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaBaseVisitor;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaParser;

/**
 * Simple visitor that assembles a string from all the terminal nodes except {@code dims, typeArguments, annotation}.
 */
class PureTypeNameComposingVisitor extends DroppingJavaBaseVisitor<String> {

  @Override
  public String visitTerminal(TerminalNode node) {
    return node.getText();
  }

  @Override
  protected String aggregateResult(String aggregate, String nextResult) {
    return aggregate + nextResult;
  }

  @Override
  public String visitDims(DroppingJavaParser.DimsContext ctx) {
    return "";
  }

  @Override
  public String visitTypeArguments(DroppingJavaParser.TypeArgumentsContext ctx) {
    return "";
  }

  @Override
  public String visitAnnotation(DroppingJavaParser.AnnotationContext ctx) {
    return "";
  }

  @Override
  protected String defaultResult() {
    return "";
  }

}
