package ru.ftc.upc.testing.dropper.lang.samples;

import java.util.Vector;

/**
 * @author Toparvion
 */
@SuppressWarnings("unused")
public class TypesCombination {
  void method1() {
    System.console();
  }

  interface InnerInterface {
    void innerMethod();

    class InnerInnerClass {
      void innerInnerMethod() { }

      enum InnerInnerInnerEnum {
        ;
        static void doSomething() { }
      }

    }

    @interface InnerAnnotation {
      int method() default 2;
    }

  }
}

@SuppressWarnings("unused")
class SiblingClass {
  public double method() {
    return Math.random();
  }
}


@SuppressWarnings("unused")
enum SiblingEnum {
  ;

  SiblingEnum() {
  }

  SiblingEnum getMe() {
    return this;
  }

  interface InnerInterface {
    TypesCombination.InnerInterface.InnerInnerClass.InnerInnerInnerEnum getThatEnum();
  }

  @interface InnerAnnotation { }
}

@SuppressWarnings("unused")
interface SiblingInterface {
  Vector<Object> method(Vector<String> arg);

  enum InnerEnum {
    ;

    InnerEnum() { }
  }
}


