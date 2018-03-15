package tech.toparvion.jmint.lang;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import tech.toparvion.jmint.lang.gen.DroppingJavaLexer;
import tech.toparvion.jmint.lang.gen.DroppingJavaParser;
import tech.toparvion.jmint.model.TargetsMap;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.lang.String.format;

/**
 * @author Toparvion
 */
public abstract class DropletLoader {
  private static final Log log = LogFactory.getLog(DropletLoader.class);

  public static TargetsMap loadDroplets(String args) {
    String[] argTokens = args.split("[;,]");
    // at the moment we support only one argument file format - droplet format, thus file extension doesn't matter
    TargetsMap overallMap = new TargetsMap();
    for (String argToken : argTokens) {
      if (argToken.isEmpty()) continue;   // to support first line of composite arguments like 'DROPLETS=$DROPLETS;...'
      try {
        overallMap.putAll(loadSingleDroplet(argToken));

      } catch (IOException e) {
        log.error(format("Failed to load droplet '%s'. Skipped.", argToken), e);

      } catch (DropletFormatException e) {
        log.error(format("Error during parsing droplet '%s'. Droplet is skipped.\n%s",
                argToken, e.getMessage()), e);

      } catch (Exception e) {
        log.error(format("Failed to load droplet '%s'. Skipped.", argToken), e);
      }
    }
    return overallMap;
  }

  private static TargetsMap loadSingleDroplet(String dropletPath) throws IOException, RecognitionException {
    if (dropletPath.toLowerCase().endsWith(".jar") || dropletPath.toLowerCase().endsWith(".zip")) {
      // the argument points to composite droplet so we should first extract its content from the archive
      TargetsMap compositeTargetMap;
      ZipInputStream zis = null;
      try {
        zis = new ZipInputStream(new FileInputStream(dropletPath));
        compositeTargetMap = new TargetsMap();
        ZipEntry nextEntry;
        while ((nextEntry = zis.getNextEntry()) != null) {
          if (!nextEntry.getName().toLowerCase().endsWith(".java")) continue;     // including directories
          log.debug(format("Processing entry: %s", nextEntry.getName()));
          compositeTargetMap.putAll(parseDroplet(new NotClosingReader(zis)));
          zis.closeEntry();
        }
      } finally {
        if (zis != null) zis.close();
      }
      return compositeTargetMap;
    }

    // in case the argument is an ordinary file let's immediately pass it to ANTLR
    return parseDroplet(new FileReader(dropletPath));
  }

  private static TargetsMap parseDroplet(Reader dropletReader) throws IOException {
    CharStream charStream                 = new ANTLRInputStream(dropletReader);
    DroppingJavaLexer lexer               = new DroppingJavaLexer(charStream);
    BufferedTokenStream tokenStream       = new CommonTokenStream(lexer);
    DroppingJavaParser parser             = new DroppingJavaParser(tokenStream);
    parser.removeErrorListeners();
    parser.addErrorListener(new UnderlineErrorListener());
    ParseTree tree = parser.compilationUnit();

    DropletAssembler assembler = new DropletAssembler(tokenStream);
    ParseTreeWalker walker = new ParseTreeWalker();
    walker.walk(assembler, tree);
    return assembler.getTargetsMap();
  }

  /**
   * A dummy implementation of {@link InputStreamReader} with stubbed {@link #close()} method. This is a way to prevent
   * ANTLRInputStream from preliminary closing {@code ZipInputStream} during loading composite droplets.
   */
  private static class NotClosingReader extends InputStreamReader {

    NotClosingReader(InputStream in) {
      super(in);
    }

    @Override
    public void close() {
      /* Here we're deliberately NOPing close operation as it must (and actually will) be done
      on ZipEntry but not ZipInputStream. */
    }
  }


}
