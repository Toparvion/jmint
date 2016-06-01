package ru.ftc.upc.testing.dropper.lang;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaLexer;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaParser;
import ru.ftc.upc.testing.dropper.model.TargetsMap;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Toparvion
 */
public class DropletAssemblerTest {

  @Test
  public void nestedClassesAreRecognizedFully() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/droplets/MultiNestedClasses.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    System.out.println(actual);
    String expected = "ru.ftc.upc.testing.dropper.lang.droplets.MultiNestedClasses -> {\n" +
            "\tTargetMethod{name='method1', cutpoint=null, resultType=int, formalParams=(), text=\n" +
            "\t\treturn 1 ;\n" +
            "\t}\n" +
            "\tTargetMethod{name='method4', cutpoint=null, resultType=int, formalParams=(), text=\n" +
            "\t\treturn 4 ;\n" +
            "\t}\n" +
            "\tTargetMethod{name='method7', cutpoint=null, resultType=int, formalParams=(), text=\n" +
            "\t\treturn 7 ;\n" +
            "\t}\n" +
            "}\n" +
            "ru.ftc.upc.testing.dropper.lang.droplets.MultiNestedClasses$InnerClass1 -> {\n" +
            "\tTargetMethod{name='method2', cutpoint=null, resultType=int, formalParams=(), text=\n" +
            "\t\treturn 2 ;\n" +
            "\t}\n" +
            "\tTargetMethod{name='method3', cutpoint=null, resultType=int, formalParams=(), text=\n" +
            "\t\treturn 3 ;\n" +
            "\t}\n" +
            "}\n" +
            "ru.ftc.upc.testing.dropper.lang.droplets.MultiNestedClasses$InnerClass2 -> {\n" +
            "\tTargetMethod{name='method5', cutpoint=null, resultType=int, formalParams=(), text=\n" +
            "\t\treturn 5 ;\n" +
            "\t}\n" +
            "}\n" +
            "ru.ftc.upc.testing.dropper.lang.droplets.MultiNestedClasses$InnerClass2$InnerClass3 -> {\n" +
            "\tTargetMethod{name='method6', cutpoint=null, resultType=int, formalParams=(), text=\n" +
            "\t\treturn 6 ;\n" +
            "\t}\n" +
            "}\n";
    assertEquals(expected, actual);
  }

  @Test
  public void methodHeadersAreRecognizedFully() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/droplets/VariousMethodHeaders.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    System.out.println(actual);
    String expected = "ru.ftc.upc.testing.dropper.lang.droplets.VariousMethodHeaders -> {\n" +
            "\tTargetMethod{name='VariousMethodHeaders', cutpoint=null, resultType=null, formalParams=(), text=(empty)}\n" +
            "\tTargetMethod{name='VariousMethodHeaders', cutpoint=null, resultType=null, formalParams=(Stack param1), text=(empty)}\n" +
            "\tTargetMethod{name='VariousMethodHeaders', cutpoint=null, resultType=null, formalParams=(float arg), text=(empty)}\n" +
            "\tTargetMethod{name='method1', cutpoint=null, resultType=void, formalParams=(), text=(empty)}\n" +
            "\tTargetMethod{name='method2', cutpoint=null, resultType=void, formalParams=(int a), text=(empty)}\n" +
            "\tTargetMethod{name='method3', cutpoint=null, resultType=void, formalParams=(Map map), text=(empty)}\n" +
            "\tTargetMethod{name='method4', cutpoint=null, resultType=void, formalParams=(List xs), text=(empty)}\n" +
            "\tTargetMethod{name='method5', cutpoint=null, resultType=void, formalParams=(UUID u1, Date d2), text=(empty)}\n" +
            "\tTargetMethod{name='method6', cutpoint=null, resultType=void, formalParams=(double d1, double d2), text=(empty)}\n" +
            "\tTargetMethod{name='method7', cutpoint=null, resultType=double, formalParams=(), text=\n" +
            "\t\treturn Math . random ( ) ;\n" +
            "\t}\n" +
            "\tTargetMethod{name='method8', cutpoint=null, resultType=Set, formalParams=(EventObject eo), text=\n" +
            "\t\treturn Collections . emptySet ( ) ;\n" +
            "\t}\n" +
            "\tTargetMethod{name='method9', cutpoint=null, resultType=T, formalParams=(T source, boolean flag), text=\n" +
            "\t\treturn ( T ) new BufferedInputStream ( source ) ;\n" +
            "\t}\n" +
            "}\n" +
            "ru.ftc.upc.testing.dropper.lang.droplets.VariousMethodHeaders$InnerClass -> {\n" +
            "\tTargetMethod{name='InnerClass', cutpoint=null, resultType=null, formalParams=(Deque arg), text=(empty)}\n" +
            "}\n";
    assertEquals(expected, actual);
  }

  @Test
  public void methodBodiesAreRecognizedFully() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/droplets/MethodBodies.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    System.out.println(actual);
    String expected = "ru.ftc.upc.testing.dropper.lang.droplets.MethodBodies -> {\n" +
            "\tTargetMethod{name='MethodBodies', cutpoint=null, resultType=null, formalParams=(String name), text=\n" +
            "\t\tSystem . out . println ( \"Hello from constructor :) \" ) ;\n" +
            "\t}\n" +
            "\tTargetMethod{name='MethodBodies', cutpoint=null, resultType=null, formalParams=(), text=(empty)}\n" +
            "\tTargetMethod{name='method1', cutpoint=null, resultType=boolean, formalParams=(), text=\n" +
            "\t\tif ( Math . random ( ) > 0.5d ) { return true ; } else { return false ; }\n" +
            "\t}\n" +
            "}\n";
    assertEquals(expected, actual);
  }

  @Test
  public void enumTypesAreRecognizedFully() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/droplets/RootEnumeration.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    System.out.println(actual);
    String expected = "ru.ftc.upc.testing.dropper.lang.droplets.RootEnumeration -> {\n" +
            "\tTargetMethod{name='RootEnumeration', cutpoint=null, resultType=null, formalParams=(), text=\n" +
            "\t\tString nothing = \"I'm the most enumerated constructor ever!\" ;\n" +
            "\t}\n" +
            "\tTargetMethod{name='getByName', cutpoint=null, resultType=RootEnumeration, formalParams=(String name), text=\n" +
            "\t\tfor ( RootEnumeration rootEnum : values ( ) ) { if ( rootEnum . toString ( ) . equals ( name ) ) { return rootEnum ; } } throw new IllegalArgumentException ( \"Not found: \" + name ) ;\n" +
            "\t}\n" +
            "}\n" +
            "ru.ftc.upc.testing.dropper.lang.droplets.RootEnumeration$InnerEnum -> {\n" +
            "\tTargetMethod{name='isTheSame', cutpoint=null, resultType=boolean, formalParams=(Enum e), text=\n" +
            "\t\treturn INNER_ENUM . toString ( ) . equals ( e . toString ( ) ) ;\n" +
            "\t}\n" +
            "}\n";
    assertEquals(expected, actual);
  }

  @Test
  public void interfaceTypesAreRecognizedFully() throws Exception {
    String dropletPath = "src/test/resources/RootInterface.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    System.out.println(actual);
    String expected = "ru.ftc.upc.testing.dropper.lang.RootInterface -> {\n" +
            "\tTargetMethod{name='method1', cutpoint=null, resultType=void, formalParams=(), text=(empty)}\n" +
            "\tTargetMethod{name='method2', cutpoint=null, resultType=boolean, formalParams=(int two), text=(empty)}\n" +
            "\tTargetMethod{name='method3', cutpoint=null, resultType=RootInterface, formalParams=(Set longs), text=(empty)}\n" +
            "\tTargetMethod{name='method5', cutpoint=null, resultType=double, formalParams=(Float param1), text=\n" +
            "\t\treturn Math . random ( ) ;\n" +
            "\t}\n" +
            "\tTargetMethod{name='newObservable', cutpoint=null, resultType=Observable, formalParams=(List lof), text=\n" +
            "\t\treturn new Observable ( ) ;\n" +
            "\t}\n" +
            "}\n" +
            "ru.ftc.upc.testing.dropper.lang.RootInterface$InnerIface -> {\n" +
            "\tTargetMethod{name='method1', cutpoint=null, resultType=InputStream, formalParams=(), text=\n" +
            "\t\treturn new FileInputStream ( \"\" ) ;\n" +
            "\t}\n" +
            "}\n" +
            "ru.ftc.upc.testing.dropper.lang.RootInterface$InnerIface$InnerInnerIface -> {\n" +
            "\tTargetMethod{name='method1', cutpoint=null, resultType=OutputStream, formalParams=(), text=\n" +
            "\t\treturn new FileOutputStream ( \"\" ) ;\n" +
            "\t}\n" +
            "}\n";
    assertEquals(expected, actual);
  }

  @Test
  public void typesCombinationsAreRecognizedFully() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/droplets/TypesCombination.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    System.out.println(actual);
    String expected = "ru.ftc.upc.testing.dropper.lang.droplets.SiblingClass -> {\n" +
            "\tTargetMethod{name='method', cutpoint=null, resultType=double, formalParams=(), text=\n" +
            "\t\treturn Math . random ( ) ;\n" +
            "\t}\n" +
            "}\n" +
            "ru.ftc.upc.testing.dropper.lang.droplets.SiblingEnum -> {\n" +
            "\tTargetMethod{name='SiblingEnum', cutpoint=null, resultType=null, formalParams=(), text=(empty)}\n" +
            "\tTargetMethod{name='getMe', cutpoint=null, resultType=SiblingEnum, formalParams=(), text=\n" +
            "\t\treturn this ;\n" +
            "\t}\n" +
            "}\n" +
            "ru.ftc.upc.testing.dropper.lang.droplets.SiblingEnum$InnerInterface -> {\n" +
            "\tTargetMethod{name='getThatEnum', cutpoint=null, resultType=TypesCombination.InnerInterface.InnerInnerClass.InnerInnerInnerEnum, formalParams=(), text=(empty)}\n" +
            "}\n" +
            "ru.ftc.upc.testing.dropper.lang.droplets.SiblingInterface -> {\n" +
            "\tTargetMethod{name='method', cutpoint=null, resultType=Vector, formalParams=(Vector arg), text=(empty)}\n" +
            "}\n" +
            "ru.ftc.upc.testing.dropper.lang.droplets.SiblingInterface$InnerEnum -> {\n" +
            "\tTargetMethod{name='InnerEnum', cutpoint=null, resultType=null, formalParams=(), text=(empty)}\n" +
            "}\n" +
            "ru.ftc.upc.testing.dropper.lang.droplets.TypesCombination -> {\n" +
            "\tTargetMethod{name='method1', cutpoint=null, resultType=void, formalParams=(), text=\n" +
            "\t\tSystem . console ( ) ;\n" +
            "\t}\n" +
            "}\n" +
            "ru.ftc.upc.testing.dropper.lang.droplets.TypesCombination$InnerInterface -> {\n" +
            "\tTargetMethod{name='innerMethod', cutpoint=null, resultType=void, formalParams=(), text=(empty)}\n" +
            "}\n" +
            "ru.ftc.upc.testing.dropper.lang.droplets.TypesCombination$InnerInterface$InnerInnerClass -> {\n" +
            "\tTargetMethod{name='innerInnerMethod', cutpoint=null, resultType=void, formalParams=(), text=(empty)}\n" +
            "}\n" +
            "ru.ftc.upc.testing.dropper.lang.droplets.TypesCombination$InnerInterface$InnerInnerClass$InnerInnerInnerEnum -> {\n" +
            "\tTargetMethod{name='doSomething', cutpoint=null, resultType=void, formalParams=(), text=(empty)}\n" +
            "}\n";
    assertEquals(expected, actual);
  }

  @Test
  public void importMapIsComposedCorrectly() throws Exception {
    String dropletPath = "src/test/resources/Imports.java";
    Map<String, String> importsMap = loadDroplet(dropletPath).getImportsMap();
    String expected = "\tCurrency -> java.util\n" +
            "\tBASE64Encoder -> sun.misc\n";
    String actual = map2Str(importsMap);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test(expected = DropletFormatException.class)
  public void importsOnDemandAreNotSupported() throws Exception {
    String dropletPath = "src/test/resources/FaultyImports.java";
    Map<String, String> importsMap = loadDroplet(dropletPath).getImportsMap();

    String actual = map2Str(importsMap);
    System.out.println(actual);
  }

  private DropletAssembler loadDroplet(String dropletPath) throws IOException {
    ANTLRFileStream fileStream = new ANTLRFileStream(dropletPath);
    DroppingJavaLexer lexer = new DroppingJavaLexer(fileStream);
    TokenStream tokenStream = new CommonTokenStream(lexer);
    DroppingJavaParser parser = new DroppingJavaParser(tokenStream);
    ParseTree tree = parser.compilationUnit();

    DropletAssembler listener = new DropletAssembler();
    ParseTreeWalker walker = new ParseTreeWalker();
    walker.walk(listener, tree);

    return listener;
  }

  private String map2Str(Map<String, String> map) {
    Set<Map.Entry<String, String>> entries = map.entrySet();
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, String> entry : entries) {
      sb
              .append("\t")
              .append(entry.getKey())
              .append(" -> ")
              .append(entry.getValue())
              .append("\n");
    }
    return sb.toString();
  }
}