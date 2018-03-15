package tech.toparvion.jmint.lang;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import tech.toparvion.jmint.model.TargetsMap;

import static java.lang.String.format;

/**
 * @author Toparvion
 */
public class DropletLoaderTest {
  private static final Log log = LogFactory.getLog(DropletLoaderTest.class);

  @Test
  public void loadDroplets() {
    String args = "src/test/resources/DPClientImpl.java;src/test/resources/DPClientImpl2.java";
    TargetsMap targetsMap = DropletLoader.loadDroplets(args);
    Assert.assertTrue((targetsMap != null)
            && (targetsMap.size() == 1)
            && (targetsMap.values().iterator().next().size() == 2));
    log.info(format("Targets map:\n%s", targetsMap.toString()));
  }

}