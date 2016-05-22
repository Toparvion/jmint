package ru.ftc.upc.testing.dropper.lang;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaLexer;
import ru.ftc.upc.testing.dropper.lang.gen.DroppingJavaParser;

import java.io.IOException;

/**
 * @author Toparvion
 */
public class DropletChecker {

  public static void main(String[] args) throws IOException {
    ANTLRFileStream fileStream = new ANTLRFileStream("C:\\lang\\dropper\\src\\main\\java\\ru\\ftc\\upc\\testing\\dropper\\PatchingTransformer.java");
    DroppingJavaLexer lexer = new DroppingJavaLexer(fileStream);
    TokenStream tokenStream = new CommonTokenStream(lexer);
    DroppingJavaParser parser = new DroppingJavaParser(tokenStream);
    ParseTree tree = parser.compilationUnit();

    DropperTreeListener listener = new DropperTreeListener();
    ParseTreeWalker walker = new ParseTreeWalker();
    walker.walk(listener, tree);
  }
}
