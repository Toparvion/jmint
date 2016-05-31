package ru.ftc.upc.testing.dropper.lang;

import java.io.*;
import java.util.List;
import java.util.Observable;
import java.util.Set;

/**
 * This class is isolated in resources test directory because it uses Java 8 language features (default and static
 * methods) that are not supported in the rest project (as it designed to be compatible with JDK 1.6+ applications).
 * @author Toparvion
 */
@SuppressWarnings("unused")
public interface RootInterface {

  void method1();

  boolean method2(int two);

  RootInterface method3(Set<Long> longs);

  default double method5(Float param1) {
    return Math.random();
  }

  static Observable newObservable(List<Object> lof) {
    return new Observable();
  }

  interface InnerIface {

    static InputStream method1() {
      return new FileInputStream("");
    }

    interface InnerInnerIface {

      default OutputStream method1() {
        return new FileOutputStream("");
      }
    }
  }

}
