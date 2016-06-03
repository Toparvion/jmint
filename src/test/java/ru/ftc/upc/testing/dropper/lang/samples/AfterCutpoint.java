package ru.ftc.upc.testing.dropper.lang.samples;

import java.util.Collections;
import java.util.Observable;
import java.util.Set;

/**
 * @author Toparvion
 */
public class AfterCutpoint {

  /**
   * A stub.
   *
   * @param path path
   * @param count  count
   * @return immutable empty set
   * @throws Exception never
   *
   * @cutpoint AFTER
   */
  public Set<Observable> fetchObservables(String path, long count) throws Exception {
    return Collections.emptySet();
  }

}
