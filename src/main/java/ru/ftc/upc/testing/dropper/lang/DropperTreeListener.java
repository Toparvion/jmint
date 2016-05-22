package ru.ftc.upc.testing.dropper.lang;

import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaBaseListener;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaParser;

/**
 * @author Toparvion
 */
public class DropperTreeListener extends DroppingJavaBaseListener {

  @Override
  public void enterVariableDeclaratorId(DroppingJavaParser.VariableDeclaratorIdContext ctx) {
    System.out.println("Method formal parameter name found: " + ctx.getText());
  }

  @Override
  public void enterClassName(DroppingJavaParser.ClassNameContext ctx) {
    System.out.println("Class name found: " + ctx.Identifier().getText());
  }

  @Override
  public void enterSingleTypeImportDeclaration(DroppingJavaParser.SingleTypeImportDeclarationContext ctx) {
    System.out.printf("Single import found: %s\n", ctx.typeName().getText());
  }

  @Override
  public void enterMethodName(DroppingJavaParser.MethodNameContext ctx) {
    System.out.printf("Method name found: %s\n", ctx.Identifier().getText());
  }

  @Override
  public void enterConstructorName(DroppingJavaParser.ConstructorNameContext ctx) {
    System.out.printf("Constructor name found: %s\n", ctx.Identifier().getText());
  }
}
