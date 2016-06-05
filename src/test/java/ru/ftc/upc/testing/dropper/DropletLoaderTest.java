package ru.ftc.upc.testing.dropper;

import org.junit.Test;
import ru.ftc.upc.testing.dropper.model.TargetsMap;

/**
 * @author Toparvion
 */
public class DropletLoaderTest {

  @Test
  public void loadDroplets() throws Exception {
    String args = "src/test/resources/DPClientImpl.java;src/test/resources/DPClientImpl2.java";
    TargetsMap targetsMap = DropletLoader.loadDroplets(args);
    System.out.printf("Targets map:\n%s", targetsMap.toString());
  }

}