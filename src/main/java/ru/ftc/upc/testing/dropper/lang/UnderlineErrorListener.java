package ru.ftc.upc.testing.dropper.lang;

import org.antlr.v4.runtime.*;

public class UnderlineErrorListener extends BaseErrorListener {

  @Override
  public void syntaxError(Recognizer<?, ?> recognizer,
                          Object offendingSymbol,
                          int line,
                          int charPositionInLine,
                          String msg,
                          RecognitionException e) {
    String underline = underlineError(recognizer, (Token) offendingSymbol, line, charPositionInLine);
    String message = String.format("Line %d:%d - %s%s", line, charPositionInLine, msg, underline);
    throw new DropletFormatException(message);
  }

  private String underlineError(Recognizer recognizer,
                              Token offendingToken, int line,
                              int charPositionInLine) {
    try {
      CommonTokenStream tokens = (CommonTokenStream) recognizer.getInputStream();
      String input = tokens.getTokenSource().getInputStream().toString();
      String[] lines = input.split("\n");
      String errorLine = lines[line - 1];
      StringBuilder sb = new StringBuilder(":\n")
              .append(errorLine)
              .append("\n");
      for (int i = 0; i < charPositionInLine; i++)
        sb.append(" ");
      int start = offendingToken.getStartIndex();
      int stop = offendingToken.getStopIndex();
      if (start >= 0 && stop >= 0) {
        for (int i = start; i <= stop; i++)
          sb.append("^");
      }
      return sb.append("\n").toString();

    } catch (Exception e) {
      // Due to method bodies collapsing exact error symbol locating is not always possible, for example when the
      // offending symbol goes after collapsed body. In such cases we don't provide underlining (leaving it empty).
      return "\n";
    }
  }
}