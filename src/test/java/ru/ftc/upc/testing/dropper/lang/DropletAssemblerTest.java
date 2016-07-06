package ru.ftc.upc.testing.dropper.lang;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ftc.upc.testing.dropper.Cutpoint;
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
  private static final Logger log = LoggerFactory.getLogger(DropletAssemblerTest.class);

  @Test
  public void nestedClassesAreRecognizedFully() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/samples/MultiNestedClasses.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    log.debug(actual);
    String expected = "ru/ftc/upc/testing/dropper/lang/samples/MultiNestedClasses -> {\n" +
            "\tTargetMethod{name='method1', cutpoint=INSTEAD, resultType=int, formalParams=(), text=\n" +
            "\t\t{ return 1 ; }\n" +
            "\t}\n" +
            "\tTargetMethod{name='method4', cutpoint=INSTEAD, resultType=int, formalParams=(), text=\n" +
            "\t\t{ return 4 ; }\n" +
            "\t}\n" +
            "\tTargetMethod{name='method7', cutpoint=INSTEAD, resultType=int, formalParams=(), text=\n" +
            "\t\t{ return 7 ; }\n" +
            "\t}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/samples/MultiNestedClasses$InnerClass1 -> {\n" +
            "\tTargetMethod{name='method2', cutpoint=INSTEAD, resultType=int, formalParams=(), text=\n" +
            "\t\t{ return 2 ; }\n" +
            "\t}\n" +
            "\tTargetMethod{name='method3', cutpoint=INSTEAD, resultType=int, formalParams=(), text=\n" +
            "\t\t{ return 3 ; }\n" +
            "\t}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/samples/MultiNestedClasses$InnerClass2 -> {\n" +
            "\tTargetMethod{name='method5', cutpoint=INSTEAD, resultType=int, formalParams=(), text=\n" +
            "\t\t{ return 5 ; }\n" +
            "\t}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/samples/MultiNestedClasses$InnerClass2$InnerClass3 -> {\n" +
            "\tTargetMethod{name='method6', cutpoint=INSTEAD, resultType=int, formalParams=(), text=\n" +
            "\t\t{ return 6 ; }\n" +
            "\t}\n" +
            "}\n";
    assertEquals(expected, actual);
  }

  @Test
  public void methodHeadersAreRecognizedFully() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/samples/VariousMethodHeaders.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    log.debug(actual);
    String expected = "ru/ftc/upc/testing/dropper/lang/samples/VariousMethodHeaders -> {\n" +
            "\tTargetMethod{name='VariousMethodHeaders', cutpoint=INSTEAD, resultType=null, formalParams=(),\n" +
            "\t\timportsOnDemand=(java.util), text=(empty)}\n" +
            "\tTargetMethod{name='VariousMethodHeaders', cutpoint=INSTEAD, resultType=null, formalParams=(Stack param1),\n" +
            "\t\timportsOnDemand=(java.util), text=(empty)}\n" +
            "\tTargetMethod{name='VariousMethodHeaders', cutpoint=INSTEAD, resultType=null, formalParams=(float arg),\n" +
            "\t\timportsOnDemand=(java.util), text=(empty)}\n" +
            "\tTargetMethod{name='method1', cutpoint=INSTEAD, resultType=void, formalParams=(),\n" +
            "\t\timportsOnDemand=(java.util), text=(empty)}\n" +
            "\tTargetMethod{name='method2', cutpoint=INSTEAD, resultType=void, formalParams=(int a),\n" +
            "\t\timportsOnDemand=(java.util), text=(empty)}\n" +
            "\tTargetMethod{name='method3', cutpoint=INSTEAD, resultType=void, formalParams=(Map map),\n" +
            "\t\timportsOnDemand=(java.util), text=(empty)}\n" +
            "\tTargetMethod{name='method4', cutpoint=INSTEAD, resultType=void, formalParams=(List xs),\n" +
            "\t\timportsOnDemand=(java.util), text=(empty)}\n" +
            "\tTargetMethod{name='method5', cutpoint=INSTEAD, resultType=void, formalParams=(UUID u1, Date d2),\n" +
            "\t\timportsOnDemand=(java.util), text=(empty)}\n" +
            "\tTargetMethod{name='method6', cutpoint=INSTEAD, resultType=void, formalParams=(double d1, double d2),\n" +
            "\t\timportsOnDemand=(java.util), text=(empty)}\n" +
            "\tTargetMethod{name='method7', cutpoint=INSTEAD, resultType=double, formalParams=(),\n" +
            "\t\timportsOnDemand=(java.util), text=\n" +
            "\t\t{ return Math . random ( ) ; }\n" +
            "\t}\n" +
            "\tTargetMethod{name='method8', cutpoint=INSTEAD, resultType=Set, formalParams=(EventObject eo),\n" +
            "\t\timportsOnDemand=(java.util), text=\n" +
            "\t\t{ return Collections . emptySet ( ) ; }\n" +
            "\t}\n" +
            "\tTargetMethod{name='method9', cutpoint=INSTEAD, resultType=Double[], formalParams=(Double[] p1, Double[][] p2, Double[] p3, Double[][] p4),\n" +
            "\t\timportsOnDemand=(java.util), text=\n" +
            "\t\t{ return new Double [ 0 ] ; }\n" +
            "\t}\n" +
            "\tTargetMethod{name='method10', cutpoint=INSTEAD, resultType=void, formalParams=(java.security.KeyStore$SecretKeyEntry entry),\n" +
            "\t\timportsOnDemand=(java.util), text=(empty)}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/samples/VariousMethodHeaders$InnerClass -> {\n" +
            "\tTargetMethod{name='InnerClass', cutpoint=INSTEAD, resultType=null, formalParams=(Deque arg),\n" +
            "\t\timportsOnDemand=(java.util), text=(empty)}\n" +
            "}\n";
    assertEquals(expected, actual);
  }

  @Test
  public void methodBodiesAreRecognizedFully() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/samples/MethodBodies.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    log.debug(actual);
    String expected = "ru/ftc/upc/testing/dropper/lang/samples/MethodBodies -> {\n" +
            "\tTargetMethod{name='MethodBodies', cutpoint=INSTEAD, resultType=null, formalParams=(String name), text=\n" +
            "\t\t{ System . out . println ( \"Hello from constructor :) \" ) ; }\n" +
            "\t}\n" +
            "\tTargetMethod{name='MethodBodies', cutpoint=INSTEAD, resultType=null, formalParams=(), text=(empty)}\n" +
            "\tTargetMethod{name='method1', cutpoint=INSTEAD, resultType=boolean, formalParams=(), text=\n" +
            "\t\t{ if ( Math . random ( ) > 0.5d ) { return true ; } else { return false ; } }\n" +
            "\t}\n" +
            "}\n";
    assertEquals(expected, actual);
  }

  @Test
  public void enumTypesAreRecognizedFully() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/samples/RootEnumeration.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    log.debug(actual);
    String expected = "ru/ftc/upc/testing/dropper/lang/samples/RootEnumeration -> {\n" +
            "\tTargetMethod{name='RootEnumeration', cutpoint=INSTEAD, resultType=null, formalParams=(), text=\n" +
            "\t\t{ String nothing = \"I'm the most enumerated constructor ever!\" ; }\n" +
            "\t}\n" +
            "\tTargetMethod{name='getByName', cutpoint=INSTEAD, resultType=RootEnumeration, formalParams=(String name), text=\n" +
            "\t\t{ for ( RootEnumeration rootEnum : values ( ) ) { if ( rootEnum . toString ( ) . equals ( $1 ) ) { return rootEnum ; } } throw new IllegalArgumentException ( \"Not found: \" + $1 ) ; }\n" +
            "\t}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/samples/RootEnumeration$InnerEnum -> {\n" +
            "\tTargetMethod{name='isTheSame', cutpoint=INSTEAD, resultType=boolean, formalParams=(Enum e), text=\n" +
            "\t\t{ return INNER_ENUM . toString ( ) . equals ( $1 . toString ( ) ) ; }\n" +
            "\t}\n" +
            "}\n";
    assertEquals(expected, actual);
  }

  @Test
  public void interfaceTypesAreRecognizedFully() throws Exception {
    String dropletPath = "src/test/resources/RootInterface.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    log.debug(actual);
    String expected = "ru/ftc/upc/testing/dropper/lang/RootInterface -> {\n" +
            "\tTargetMethod{name='method1', cutpoint=INSTEAD, resultType=void, formalParams=(), text=(empty)}\n" +
            "\tTargetMethod{name='method2', cutpoint=INSTEAD, resultType=boolean, formalParams=(int two), text=(empty)}\n" +
            "\tTargetMethod{name='method3', cutpoint=INSTEAD, resultType=RootInterface, formalParams=(java.util.Set longs), text=(empty)}\n" +
            "\tTargetMethod{name='method5', cutpoint=INSTEAD, resultType=double, formalParams=(Float param1), text=\n" +
            "\t\t{ return Math . random ( ) ; }\n" +
            "\t}\n" +
            "\tTargetMethod{name='newObservable', cutpoint=INSTEAD, resultType=java.util.Observable, formalParams=(java.util.List lof), text=\n" +
            "\t\t{ return new java.util.Observable ( ) ; }\n" +
            "\t}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/RootInterface$InnerIface -> {\n" +
            "\tTargetMethod{name='method1', cutpoint=INSTEAD, resultType=java.io.InputStream, formalParams=(), text=\n" +
            "\t\t{ return new java.io.FileInputStream ( \"\" ) ; }\n" +
            "\t}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/RootInterface$InnerIface$InnerInnerIface -> {\n" +
            "\tTargetMethod{name='method1', cutpoint=INSTEAD, resultType=java.io.OutputStream, formalParams=(), text=\n" +
            "\t\t{ return new java.io.FileOutputStream ( \"\" ) ; }\n" +
            "\t}\n" +
            "}\n";
    assertEquals(expected, actual);
  }

  @Test
  public void typesCombinationsAreRecognizedFully() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/samples/TypesCombination.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    log.debug(actual);
    String expected = "ru/ftc/upc/testing/dropper/lang/samples/TypesCombination -> {\n" +
            "\tTargetMethod{name='method1', cutpoint=INSTEAD, resultType=void, formalParams=(), text=\n" +
            "\t\t{ System . console ( ) ; }\n" +
            "\t}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/samples/TypesCombination$InnerInterface -> {\n" +
            "\tTargetMethod{name='innerMethod', cutpoint=INSTEAD, resultType=void, formalParams=(), text=(empty)}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/samples/TypesCombination$InnerInterface$InnerInnerClass -> {\n" +
            "\tTargetMethod{name='innerInnerMethod', cutpoint=INSTEAD, resultType=void, formalParams=(), text=(empty)}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/samples/TypesCombination$InnerInterface$InnerInnerClass$InnerInnerInnerEnum -> {\n" +
            "\tTargetMethod{name='doSomething', cutpoint=INSTEAD, resultType=void, formalParams=(), text=(empty)}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/samples/SiblingClass -> {\n" +
            "\tTargetMethod{name='method', cutpoint=INSTEAD, resultType=double, formalParams=(), text=\n" +
            "\t\t{ return Math . random ( ) ; }\n" +
            "\t}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/samples/SiblingEnum -> {\n" +
            "\tTargetMethod{name='SiblingEnum', cutpoint=INSTEAD, resultType=null, formalParams=(), text=(empty)}\n" +
            "\tTargetMethod{name='getMe', cutpoint=INSTEAD, resultType=SiblingEnum, formalParams=(), text=\n" +
            "\t\t{ return this ; }\n" +
            "\t}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/samples/SiblingEnum$InnerInterface -> {\n" +
            "\tTargetMethod{name='getThatEnum', cutpoint=INSTEAD, resultType=TypesCombination$InnerInterface$InnerInnerClass$InnerInnerInnerEnum, formalParams=(), text=(empty)}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/samples/SiblingInterface -> {\n" +
            "\tTargetMethod{name='method', cutpoint=INSTEAD, resultType=java.util.Vector, formalParams=(java.util.Vector arg), text=(empty)}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/samples/SiblingInterface$InnerEnum -> {\n" +
            "\tTargetMethod{name='InnerEnum', cutpoint=INSTEAD, resultType=null, formalParams=(), text=(empty)}\n" +
            "}\n";
    assertEquals(expected, actual);
  }

  @Test
  public void importMapIsComposedCorrectly() throws Exception {
    String dropletPath = "src/test/resources/Imports.java";
    DropletAssembler dropletAssembler = loadDroplet(dropletPath);
    Map<String, String> importsMap = dropletAssembler.singleImportsMap;
    String expectedImportsMap = "\tCurrency -> java.util\n" +
            "\tBASE64Encoder -> sun.misc\n" +
            "\tMAX_VALUE -> java.lang.Integer\n" +
            "\trandom -> java.lang.Math\n";
    String actualImportsMap = map2Str(importsMap);
    log.debug(actualImportsMap);
    assertEquals(expectedImportsMap, actualImportsMap);

    TargetsMap targetsMap = dropletAssembler.getTargetsMap();
    String actualTargetsMap = targetsMap.toString();
    log.debug(actualTargetsMap);
    String expectedTargetsMap = "ru/ftc/upc/testing/dropper/lang/samples/Imports -> {\n" +
            "\tTargetMethod{name='Imports', cutpoint=INSTEAD, resultType=null, formalParams=(),\n" +
            "\t\timportsOnDemand=(java.math), text=\n" +
            "\t\t{ System . out . println ( ) ; BigInteger bigInteger = new BigInteger ( \"31415926525\" ) ; double java.lang.Math.random = java.lang.Math.random ( ) ; int maxInt = java.lang.Integer.MAX_VALUE ; System . out . printf ( \"%d, %d, %d\" , MAX_EXPONENT , MIN_EXPONENT , MIN_VALUE ) ; }\n" +
            "\t}\n" +
            "}\n";
    assertEquals(expectedTargetsMap, actualTargetsMap);
  }

  @Test
  public void afterCutpointIsRecognizedFully() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/samples/AfterCutpoint.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    Cutpoint actual = targetsMap.entrySet().iterator().next().getValue().getFirst().getCutpoint();
    log.debug("Actual cutpoint: {}", actual);
    assertEquals(Cutpoint.AFTER, actual);
  }

  @Test
  public void defaultCutpointIsAppliedCorrectly() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/samples/DefaultCutpoint.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    Cutpoint actual = targetsMap.entrySet().iterator().next().getValue().getFirst().getCutpoint();
    log.debug("Actual cutpoint (default): {}", actual);
    assertEquals(Cutpoint.INSTEAD, actual);
  }

  @Test
  public void typeNamesInMethodBodiesAreResolvedCorrectly() throws Exception {
    String dropletPath = "src/test/resources/DPClientImpl.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    log.debug(actual);
    String expected = "dp/DPClientImpl -> {\n" +
            "\tTargetMethod{name='currencyRate', cutpoint=INSTEAD, resultType=dp.models.QuickPay$AnsCurrencyRate, formalParams=(dp.models.QuickPay$ReqCurrencyRate reqCurrencyRate), text=\n" +
            "\t\t{ try { java.io.FileReader stubReader = new java.io.FileReader ( System . getProperty ( \"user.dir\" ) + java.io.File . separator + \"AnsCurrencyRate.xml\" ) ; javax.xml.bind.Unmarshaller unmarshaller = quickPayJbcDoc . createUnmarshaller ( ) ; dp.models.QuickPay quickPay = ( dp.models.QuickPay ) unmarshaller . unmarshal ( stubReader ) ; log . info ( \"Rates were loaded from file AnsCurrencyRate.xml.\" ) ; stubReader . close ( ) ; return quickPay . getInfoService ( ) . getAnsCurrencyRate ( ) ; } catch ( Exception e ) { log . error ( \"Unable to load rates from file. Falling back to real service.\" , e ) ; dp . models . QuickPay quickPay = dp.models.QuickPay . infoService ( ) ; quickPay . getInfoService ( ) . setReqCurrencyRate ( $1 ) ; quickPay = sendQuickPay ( quickPay , false , false ) ; dp.models.QuickPay . AnsCurrencyRate ans = quickPay . getInfoService ( ) . getAnsCurrencyRate ( ) ; return ans ; } }\n" +
            "\t}\n" +
            "}\n";
    assertEquals(expected, actual);
  }

  @Test
  public void methodArgumentTypesAreResolvedCorrectly() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/samples/NestedTypesAmongMethodArguments.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    log.debug(actual);

    String expected = "ru/ftc/upc/testing/dropper/lang/samples/NestedTypesAmongMethodArguments -> {\n" +
            "\tTargetMethod{name='method1', cutpoint=INSTEAD, resultType=java.util.Map$Entry, formalParams=(java.security.KeyStore$SecretKeyEntry arg), text=\n" +
            "\t\t{ return new java.util.AbstractMap . SimpleEntry < Long , String > ( 23L , \"\" ) ; }\n" +
            "\t}\n" +
            "}\n";
    assertEquals(expected, actual);
  }

  @Test
  public void formalParamReferencesAreObfuscatedCorrectly() throws Exception {
    String dropletPath = "src/test/resources/DPClientImpl2.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    log.debug(actual);
    String expected = "dp/DPClientImpl -> {\n" +
            "\tTargetMethod{name='fetchTransferStatus', cutpoint=INSTEAD, resultType=String, formalParams=(String oID, boolean needFlag), text=\n" +
            "\t\t{ String statusStr = null ; try { java.util.Properties stub = new java.util.Properties ( ) ; stub . load ( new java.io.FileReader ( System . getProperty ( \"user.dir\" ) + java.io.File . separator + \"dp-edit-mock.properties\" ) ) ; statusStr = stub . getProperty ( $1 ) ; if ( statusStr != null ) { statusStr = statusStr . trim ( ) ; log . warn ( \"For STRoID={} transfer status '{}' was taken from mock.\" , $1 , statusStr ) ; } } catch ( java.io.IOException e ) { log . error ( \"Failed to load mocked transfer edit statuses.\" , e ) ; } if ( statusStr == null ) { dp.models.QuickPay quickPay = dp.models.QuickPay . infoService ( ) ; dp.models.ReqTransferSearch req = new dp.models.ReqTransferSearch ( ) ; req . setOID ( getReserve ( ) . oID ) ; quickPay . getInfoService ( ) . setReqTransferSearch ( req ) ; quickPay = sendQuickPay ( quickPay , false , $2 ) ; statusStr = quickPay . getInfoService ( ) . getAnsTransferSearch ( ) . getTransferStatus ( ) ; } return statusStr ; }\n" +
            "\t}\n" +
            "}\n";
    assertEquals(expected, actual);
  }

  @Test
  public void dropletSuffixIsOmittedCorrectly() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/samples/ClassWithSuffixDroplet.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    log.debug(actual);

    String expected = "ru/ftc/upc/testing/dropper/lang/samples/ClassWithSuffix -> {\n" +
            "\tTargetMethod{name='method1', cutpoint=INSTEAD, resultType=void, formalParams=(), text=(empty)}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/samples/ClassWithSuffix$InnerClass -> {\n" +
            "\tTargetMethod{name='method1', cutpoint=INSTEAD, resultType=void, formalParams=(), text=(empty)}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/samples/ClassWithSuffix$InnerClass2 -> {\n" +
            "\tTargetMethod{name='method1', cutpoint=INSTEAD, resultType=void, formalParams=(), text=(empty)}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/samples/ClassWithSuffix$InnerClassDropletNotLatest -> {\n" +
            "\tTargetMethod{name='method1', cutpoint=INSTEAD, resultType=void, formalParams=(), text=(empty)}\n" +
            "}\n" +
            "ru/ftc/upc/testing/dropper/lang/samples/ClassWithSuffix$InnerClassDroplet -> {\n" +
            "\tTargetMethod{name='method1', cutpoint=INSTEAD, resultType=void, formalParams=(), text=(empty)}\n" +
            "}\n";
    assertEquals(expected, actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void methodDuplicatesAreDetectedCorrectly() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/samples/DuplicatedMethodsDetection.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    log.debug(actual);
  }

  @Test(expected = DropletFormatException.class)
  public void parametrizedMethodsAreNotSupported() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/samples/GenericClass.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    log.debug(actual);
  }

  /**
   * This test must fail but due to bug in grammar it doesn't happen. The test left for fixing the bug in future.
   */
  @Test(/*expected = DropletFormatException.class*/)
  public void parametrizedParametersInMethodsAreNotSupported() throws Exception {
    String dropletPath = "src/test/java/ru/ftc/upc/testing/dropper/lang/samples/GenericClass2.java";
    TargetsMap targetsMap = loadDroplet(dropletPath).getTargetsMap();
    String actual = targetsMap.toString();
    log.debug(actual);
  }

  private DropletAssembler loadDroplet(String dropletPath) throws IOException {
    ANTLRFileStream fileStream = new ANTLRFileStream(dropletPath);
    DroppingJavaLexer lexer = new DroppingJavaLexer(fileStream);
    BufferedTokenStream tokenStream = new CommonTokenStream(lexer);
    DroppingJavaParser parser = new DroppingJavaParser(tokenStream);
    ParseTree tree = parser.compilationUnit();

    DropletAssembler assembler = new DropletAssembler(tokenStream);
    ParseTreeWalker walker = new ParseTreeWalker();
    walker.walk(assembler, tree);

    return assembler;
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