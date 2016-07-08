package tech.toparvion.jmint.lang.samples;

/**
 * @author Toparvion
 */
@SuppressWarnings("unused")
public class MethodBodies {

  public MethodBodies(String name) {
    System.out.println("Hello from constructor :) ");
  }

  public MethodBodies() {

  }

  public boolean method1() {
    //noinspection RedundantIfStatement
    if (Math.random() > 0.5d) {
      return true;
    } else {
      return false;
    }
  }

}
