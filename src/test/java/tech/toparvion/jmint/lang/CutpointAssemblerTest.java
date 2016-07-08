package tech.toparvion.jmint.lang;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.toparvion.jmint.Cutpoint;
import tech.toparvion.jmint.lang.gen.JavadocLexer;
import tech.toparvion.jmint.lang.gen.JavadocParser;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Toparvion
 */
public class CutpointAssemblerTest {
  static final Logger log = LoggerFactory.getLogger(CutpointAssemblerTest.class); 

  @Test
  public void cutpoint1IsRecognizedCorrectly() throws Exception {
    CutpointAssembler cutpointAssembler = loadJavadoc("src/test/resources/Cutpoint_1.javadoc");
    String tagValue = cutpointAssembler.getTagValue();
    Cutpoint cutpoint = Cutpoint.valueOf(tagValue.toUpperCase());
    log.info("Cutpoint 1: '{}'", tagValue);
    assertEquals(Cutpoint.BEFORE, cutpoint);
  }

  @Test
  public void cutpoint2IsRecognizedCorrectly() throws Exception {
    CutpointAssembler cutpointAssembler = loadJavadoc("src/test/resources/Cutpoint_2.javadoc");
    String tagValue = cutpointAssembler.getTagValue();
    Cutpoint cutpoint = Cutpoint.valueOf(tagValue.toUpperCase());
    log.info("Cutpoint 2: '{}'", tagValue);
    assertEquals(Cutpoint.CATCH, cutpoint);
  }

  @Test
  public void cutpoint3IsRecognizedCorrectly() throws Exception {
    CutpointAssembler cutpointAssembler = loadJavadoc("src/test/resources/Cutpoint_3.javadoc");
    String tagValue = cutpointAssembler.getTagValue();
    Cutpoint cutpoint = Cutpoint.valueOf(tagValue.toUpperCase());
    log.info("Cutpoint 3: '{}'", tagValue);
    assertEquals(Cutpoint.INSTEAD, cutpoint);
  }

  @Test
  public void cutpoint4IsRecognizedCorrectly() throws Exception {
    CutpointAssembler cutpointAssembler = loadJavadoc("src/test/resources/Cutpoint_4.javadoc");
    String tagValue = cutpointAssembler.getTagValue();
    Cutpoint cutpoint = Cutpoint.valueOf(tagValue.toUpperCase());
    log.info("Cutpoint 4: '{}'", tagValue);
    assertEquals(Cutpoint.AFTER, cutpoint);
  }

  @Test
  public void cutpoint5IsRecognizedCorrectly() throws Exception {
    CutpointAssembler cutpointAssembler = loadJavadoc("src/test/resources/Cutpoint_5.javadoc");
    String tagValue = cutpointAssembler.getTagValue();
    log.info("Cutpoint 5: '{}'", tagValue);
    assertNull(tagValue);
  }

  @Test
  public void cutpoint6IsRecognizedCorrectly() throws Exception {
    CutpointAssembler cutpointAssembler = loadJavadoc("src/test/resources/Cutpoint_6.javadoc");
    String tagValue = cutpointAssembler.getTagValue();
    Cutpoint cutpoint = Cutpoint.valueOf(tagValue.toUpperCase());
    log.info("Cutpoint 6: '{}'", tagValue);
    assertEquals(Cutpoint.IGNORE, cutpoint);
  }

  @Test
  public void cutpoint7IsRecognizedCorrectly() throws Exception {
    CutpointAssembler cutpointAssembler = loadJavadoc("src/test/resources/Cutpoint_7.javadoc");
    String tagValue = cutpointAssembler.getTagValue();
    Cutpoint cutpoint = Cutpoint.valueOf(tagValue.toUpperCase());
    log.info("Cutpoint 7: '{}'", tagValue);
    assertEquals(Cutpoint.IGNORE, cutpoint);
  }

  private CutpointAssembler loadJavadoc(String javadocPath) throws IOException {
    ANTLRFileStream fileStream = new ANTLRFileStream(javadocPath);
    JavadocLexer lexer = new JavadocLexer(fileStream);
    TokenStream tokenStream = new CommonTokenStream(lexer);
    JavadocParser parser = new JavadocParser(tokenStream);
    ParseTree tree = parser.documentation();

    CutpointAssembler assembler = new CutpointAssembler();
    ParseTreeWalker walker = new ParseTreeWalker();
    walker.walk(assembler, tree);

    return assembler;
  }

}