package tech.toparvion.jmint.lang;

import tech.toparvion.jmint.lang.gen.JavadocParser;
import tech.toparvion.jmint.lang.gen.JavadocParserBaseListener;
import tech.toparvion.jmint.model.Cutpoint;
import tech.toparvion.jmint.model.CutpointType;

import java.util.Arrays;
import java.util.List;

import static tech.toparvion.jmint.model.Cutpoint.DEFAULT_CUTPOINT;

/**
 * @author Toparvion
 */
class CutpointAssembler extends JavadocParserBaseListener {
  private static final String CUTPOINT_TAG_NAME = "cutpoint";

  private Cutpoint cutpoint = DEFAULT_CUTPOINT;

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
    cutpoint = new Cutpoint(CutpointType.getByNameSafe(tagValueEntries[0]));
    if (tagValueEntries.length > 1) {
      cutpoint.setAuxParams(Arrays.copyOfRange(tagValueEntries, 1, tagValueEntries.length));
    }
  }

  Cutpoint getCutpoint() {
    return cutpoint;
  }
}
