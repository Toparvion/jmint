package ru.ftc.upc.testing.dropper.lang.samples;

import tech.toparvion.jmint.lang.samples.TypesCombination;

import java.util.Vector;

/**
 * This combination makes ANTLR 4.5.1 build an incorrect parse tree on DroppingJava grammar.
 * When accepting SuppressWarnings annotation just before SiblingInterface_ interface, the parser includes the whole
 * body of the interface into the value of annotation's parameter (instead of just "unused" value). The parser stops
 * "consuming" just when it faces the end of the next SuppressWarnings annotation. The reason of this behavior is
 * still unknown but the observations were done that locating the interface after the following
 * SiblingEnumeration_ enumeration makes the error disappear. Another way is to transform the interface into a class.
 * For the time being this error is not considered critical but should be investigated in order to prevent such
 * behavior in other circumstances.
 * Possible solution may be found in "The Definitive ANTLR 4 Reference", §15.6.
 *
 * @author Toparvion
 */
@SuppressWarnings("unused")
public class FaultyTypesCombination {
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
class SiblingClass_ {
  public double method() {
    return Math.random();
  }
}

@SuppressWarnings("unused")
interface SiblingInterface_ {
  Vector<Object> method(Vector<String> arg);

  enum InnerEnum {
    ;

    InnerEnum() { }
  }
}

@SuppressWarnings("unused")
enum SiblingEnum_ {
  ;

  SiblingEnum() {
  }

  SiblingEnum getMe() {
    return this;
  }

  interface InnerInterface {
    TypesCombination.InnerInterface.InnerInnerClass.InnerInnerInnerEnum getThatEnum();
  }

  @interface InnerAnnotation {

  }

}


