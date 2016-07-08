package tech.toparvion.jmint.lang.samples;

/**
 * Target classes may have different names that may be confusing in case several droplets define the same method for
 * modifying. To address this issue Dropper must check the duplicates itself. This sample contains the problematic
 * combination of methods.
 * @author Toparvion
 */
@SuppressWarnings("unused")
public class DuplicatedMethodsDetection {

  class InnerClassDroplet {
    void method1() {}
  }

  class InnerClass_Droplet {
    void method1() {}
  }

}
