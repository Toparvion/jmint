package ru.ftc.upc.testing.dropper.lang.samples;

import java.security.KeyStore;
import java.util.AbstractMap;
import java.util.Map;

/**
 * @author Toparvion
 */
@SuppressWarnings("unused")
public class NestedTypesAmongMethodArguments {

  protected Map.Entry<Long, String> method1(KeyStore.SecretKeyEntry arg) {
    return new AbstractMap.SimpleEntry<Long, String>(23L, "");
  }
}
