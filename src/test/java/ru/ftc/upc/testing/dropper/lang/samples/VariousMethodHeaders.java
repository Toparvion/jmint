package ru.ftc.upc.testing.dropper.lang.samples;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Toparvion
 */
@SuppressWarnings("unused")
public class VariousMethodHeaders {

  public VariousMethodHeaders() { }

  public VariousMethodHeaders(Stack<Observer> param1) { }

  VariousMethodHeaders(float arg) { }

  static class InnerClass {
    public InnerClass(Deque<Integer> arg) { }
  }

  void method1() { }

  void method2(int a) { }

  void method3(Map<String, Object> map) { }

  void method4(List... xs) { }

  void method5(UUID u1, Date... d2) { }

  void method6(double d1, double d2) { }

  double method7() {
    return Math.random();
  }

  Set<Currency> method8(EventObject eo) {
    return Collections.emptySet();
  }

  @SuppressWarnings("unchecked")
  private <T extends InputStream> T method9(T source, boolean flag)
          throws FileNotFoundException {
    return (T) new BufferedInputStream(source);
  }

  protected Map.Entry<Long, String> method10(Map.Entry<Double, Integer> arg) {
    return new AbstractMap.SimpleEntry<Long, String>(23L, "");
  }
}
