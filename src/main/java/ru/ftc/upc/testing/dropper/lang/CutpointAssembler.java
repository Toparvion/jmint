package ru.ftc.upc.testing.dropper.lang;

import ru.ftc.upc.testing.dropper.lang.gen.JavadocParser;
import ru.ftc.upc.testing.dropper.lang.gen.JavadocParserBaseListener;

import java.util.List;

/**
 * @author Toparvion
 */
public class CutpointAssembler extends JavadocParserBaseListener {

  private static final String CUTPOINT_TAG_NAME = "cutpoint";

  private String tagValue;

  @Override
  public void enterBlockTag(JavadocParser.BlockTagContext ctx) {
    String tagName = ctx.blockTagName().getText();
    if (!CUTPOINT_TAG_NAME.equalsIgnoreCase(tagName))
      return;

    List<JavadocParser.BlockTagContentContext> tagContents = ctx.blockTagContent();
    if (tagContents.isEmpty())
      return;

    JavadocParser.BlockTagContentContext tagContent = tagContents.get(0);
    String tagContentText = tagContent.getText();
    String[] tagValueEntries = tagContentText.split("[\\x20\\t]");
    /* For the time being we don't support parametrized tag values, but we assume their support in future. */
    tagValue = tagValueEntries[0];
  }

  public String getTagValue() {
    return tagValue;
  }
}
