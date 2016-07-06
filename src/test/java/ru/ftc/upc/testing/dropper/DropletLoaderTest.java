package ru.ftc.upc.testing.dropper;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ftc.upc.testing.dropper.model.TargetsMap;

/**
 * @author Toparvion
 */
public class DropletLoaderTest {
  private static final Logger log = LoggerFactory.getLogger(DropletLoaderTest.class);

  @Test
  public void loadDroplets() throws Exception {
    String args = "src/test/resources/DPClientImpl.java;src/test/resources/DPClientImpl2.java";
    TargetsMap targetsMap = DropletLoader.loadDroplets(args);
    log.info("Targets map:\n{}", targetsMap.toString());
  }

}