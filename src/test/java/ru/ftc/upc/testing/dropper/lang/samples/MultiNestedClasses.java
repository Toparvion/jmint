package ru.ftc.upc.testing.dropper.lang.samples;

/**
 * @author Toparvion
 */
@SuppressWarnings("unused")
public class MultiNestedClasses {

  int method1() {
    return 1;
  }

  class InnerClass1 {
    int method2() { return 2; }

    int method3() { return 3; }
  }

  int method4() { return 4; }

  class InnerClass2 {
    int method5() { return 5; }

    class InnerClass3 {
      int method6() { return 6; }
    }
  }

  class InnerClass4 {}

  int method7() { return 7; }

}
