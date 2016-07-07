package tech.toparvion.jmint.lang;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.toparvion.jmint.model.TargetsMap;

/**
 * @author Toparvion
 */
public class DropletLoaderTest {
  private static final Logger log = LoggerFactory.getLogger(DropletLoaderTest.class);

  @Test
  public void loadDroplets() throws Exception {
    String args = "src/test/resources/DPClientImpl.java;src/test/resources/DPClientImpl2.java";
    TargetsMap targetsMap = DropletLoader.loadDroplets(args);
    Assert.assertTrue((targetsMap != null)
            && (targetsMap.size() == 1)
            && (targetsMap.values().iterator().next().size() == 2));
    log.info("Targets map:\n{}", targetsMap.toString());
  }

}