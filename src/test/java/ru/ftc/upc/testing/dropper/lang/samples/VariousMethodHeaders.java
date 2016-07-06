package ru.ftc.upc.testing.dropper.lang.samples;

import java.security.KeyStore;
import java.util.*;

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

  Double[] method9(Double[] p1, Double[][] p2, Double p3[], Double[] p4[]) {
    return new Double[0];
  }

  void method10(KeyStore.SecretKeyEntry entry) {

  }

}
