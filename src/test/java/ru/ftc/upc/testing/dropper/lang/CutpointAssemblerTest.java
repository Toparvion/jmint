package ru.ftc.upc.testing.dropper.lang;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import ru.ftc.upc.testing.dropper.Cutpoint;
import ru.ftc.upc.testing.dropper.lang.gen.JavadocLexer;
import ru.ftc.upc.testing.dropper.lang.gen.JavadocParser;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Toparvion
 */
public class CutpointAssemblerTest {

  @Test
  public void cutpoint1IsRecognizedCorrectly() throws Exception {
    CutpointAssembler cutpointAssembler = loadJavadoc("src/test/resources/Cutpoint_1.javadoc");
    String tagValue = cutpointAssembler.getTagValue();
    Cutpoint cutpoint = Cutpoint.valueOf(tagValue.toUpperCase());
    System.out.printf("Cutpoint 1: '%s'\n", tagValue);
    assertEquals(Cutpoint.BEFORE, cutpoint);
  }

  @Test
  public void cutpoint2IsRecognizedCorrectly() throws Exception {
    CutpointAssembler cutpointAssembler = loadJavadoc("src/test/resources/Cutpoint_2.javadoc");
    String tagValue = cutpointAssembler.getTagValue();
    Cutpoint cutpoint = Cutpoint.valueOf(tagValue.toUpperCase());
    System.out.printf("Cutpoint 2: '%s'\n", tagValue);
    assertEquals(Cutpoint.CATCH, cutpoint);
  }

  @Test
  public void cutpoint3IsRecognizedCorrectly() throws Exception {
    CutpointAssembler cutpointAssembler = loadJavadoc("src/test/resources/Cutpoint_3.javadoc");
    String tagValue = cutpointAssembler.getTagValue();
    Cutpoint cutpoint = Cutpoint.valueOf(tagValue.toUpperCase());
    System.out.printf("Cutpoint 3: '%s'\n", tagValue);
    assertEquals(Cutpoint.INSTEAD, cutpoint);
  }

  @Test
  public void cutpoint4IsRecognizedCorrectly() throws Exception {
    CutpointAssembler cutpointAssembler = loadJavadoc("src/test/resources/Cutpoint_4.javadoc");
    String tagValue = cutpointAssembler.getTagValue();
    Cutpoint cutpoint = Cutpoint.valueOf(tagValue.toUpperCase());
    System.out.printf("Cutpoint 4: '%s'\n", tagValue);
    assertEquals(Cutpoint.AFTER, cutpoint);
  }

  @Test
  public void cutpoint5IsRecognizedCorrectly() throws Exception {
    CutpointAssembler cutpointAssembler = loadJavadoc("src/test/resources/Cutpoint_5.javadoc");
    String tagValue = cutpointAssembler.getTagValue();
    System.out.printf("Cutpoint 5: '%s'\n", tagValue);
    assertNull(tagValue);
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