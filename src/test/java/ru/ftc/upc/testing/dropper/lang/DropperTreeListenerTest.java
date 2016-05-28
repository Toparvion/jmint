package ru.ftc.upc.testing.dropper.lang;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaLexer;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaParser;

import java.io.IOException;

/**
 * @author Toparvion
 */
public class DropperTreeListenerTest {

  @Test
  public void nestedClassesAreRecognizedFully() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/droplets/MultiNestedClasses.java";
    TargetsMap targetsMap = loadDroplet(dropletPath);
    String expected = "MultiNestedClasses -> {\n" +
            "\tTargetMethod{name='method1', cutpoint=null, text='null', resultType=int, formalParams=()}\n" +
            "\tTargetMethod{name='method4', cutpoint=null, text='null', resultType=int, formalParams=()}\n" +
            "\tTargetMethod{name='method7', cutpoint=null, text='null', resultType=int, formalParams=()}\n" +
            "}\n" +
            "MultiNestedClasses.InnerClass1 -> {\n" +
            "\tTargetMethod{name='method2', cutpoint=null, text='null', resultType=int, formalParams=()}\n" +
            "\tTargetMethod{name='method3', cutpoint=null, text='null', resultType=int, formalParams=()}\n" +
            "}\n" +
            "MultiNestedClasses.InnerClass2 -> {\n" +
            "\tTargetMethod{name='method5', cutpoint=null, text='null', resultType=int, formalParams=()}\n" +
            "}\n" +
            "MultiNestedClasses.InnerClass2.InnerClass3 -> {\n" +
            "\tTargetMethod{name='method6', cutpoint=null, text='null', resultType=int, formalParams=()}\n" +
            "}\n";
    String actual = targetsMap.toString();
    Assert.assertEquals(expected, actual);
    System.out.println(actual);
  }

  @Test
  public void methodsAreRecognizedFully() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/droplets/VariousMethodHeaders.java";
    TargetsMap targetsMap = loadDroplet(dropletPath);
    String expected = "VariousMethodHeaders -> {\n" +
            "\tTargetMethod{name='method1', cutpoint=null, text='null', resultType=void, formalParams=()}\n" +
            "\tTargetMethod{name='method2', cutpoint=null, text='null', resultType=void, formalParams=(int a)}\n" +
            "\tTargetMethod{name='method3', cutpoint=null, text='null', resultType=void, formalParams=(Map map)}\n" +
            "\tTargetMethod{name='method4', cutpoint=null, text='null', resultType=void, formalParams=(List xs)}\n" +
            "\tTargetMethod{name='method5', cutpoint=null, text='null', resultType=void, formalParams=(UUID u1, Date d2)}\n" +
            "\tTargetMethod{name='method6', cutpoint=null, text='null', resultType=void, formalParams=(double d1, double d2)}\n" +
            "\tTargetMethod{name='method7', cutpoint=null, text='null', resultType=double, formalParams=()}\n" +
            "\tTargetMethod{name='method8', cutpoint=null, text='null', resultType=Set, formalParams=(EventObject eo)}\n" +
            "}\n";
    String actual = targetsMap.toString();
    Assert.assertEquals(expected, actual);
    System.out.println(actual);
  }

  private TargetsMap loadDroplet(String dropletPath) throws IOException {
    ANTLRFileStream fileStream = new ANTLRFileStream(dropletPath);
    DroppingJavaLexer lexer = new DroppingJavaLexer(fileStream);
    TokenStream tokenStream = new CommonTokenStream(lexer);
    DroppingJavaParser parser = new DroppingJavaParser(tokenStream);
    ParseTree tree = parser.compilationUnit();

    DropperTreeListener listener = new DropperTreeListener();
    ParseTreeWalker walker = new ParseTreeWalker();
    walker.walk(listener, tree);

    return listener.getTargetsMap();
  }
}