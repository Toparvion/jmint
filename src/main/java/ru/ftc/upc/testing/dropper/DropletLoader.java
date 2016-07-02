package ru.ftc.upc.testing.dropper;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import ru.ftc.upc.testing.dropper.lang.DropletAssembler;
import ru.ftc.upc.testing.dropper.lang.DropletFormatException;
import ru.ftc.upc.testing.dropper.lang.UnderlineErrorListener;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaLexer;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaParser;
import ru.ftc.upc.testing.dropper.model.TargetsMap;

import java.io.IOException;

/**
 * @author Toparvion
 */
abstract class DropletLoader {

  static TargetsMap loadDroplets(String args) {
    String[] argTokens = args.split("[;,]");
    // at the moment we support only one argument file format - droplet format, thus file extension doesn't matter
    TargetsMap overallMap = new TargetsMap();
    for (String argToken : argTokens) {
      try {
        overallMap.putAll(loadSingleDroplet(argToken));

      } catch (IOException e) {
        System.err.printf("Failed to load droplet '%s'. Skipped.", argToken);
        e.printStackTrace();

      } catch (DropletFormatException e) {
        System.err.printf("Error during parsing droplet '%s'. Droplet is skipped.\n%s\n", argToken, e.getMessage());

      } catch (Exception e) {
        System.err.printf("Failed to load droplet '%s'. Skipped.", argToken);
        e.printStackTrace();
      }
    }
    return overallMap;
  }

  private static TargetsMap loadSingleDroplet(String dropletPath) throws IOException, RecognitionException {
    DropletAssembler assembler = parseAndGetAssembler(dropletPath);
    return assembler.getTargetsMap();
  }

  private static DropletAssembler parseAndGetAssembler(String dropletPath) throws IOException {
    ANTLRFileStream fileStream = new ANTLRFileStream(dropletPath);
    DroppingJavaLexer lexer = new DroppingJavaLexer(fileStream);
    BufferedTokenStream tokenStream = new CommonTokenStream(lexer);
    DroppingJavaParser parser = new DroppingJavaParser(tokenStream);
    parser.removeErrorListeners();
    parser.addErrorListener(new UnderlineErrorListener());
    ParseTree tree = parser.compilationUnit();

    DropletAssembler assembler = new DropletAssembler(tokenStream);
    ParseTreeWalker walker = new ParseTreeWalker();
    walker.walk(assembler, tree);
    return assembler;
  }

}
