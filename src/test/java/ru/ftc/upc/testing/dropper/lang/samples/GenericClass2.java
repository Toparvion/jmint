package ru.ftc.upc.testing.dropper.lang.samples;

import java.util.AbstractSet;
import java.util.Iterator;

/**
 * @author Toparvion
 */
@SuppressWarnings("unused")
public class GenericClass2<T> extends AbstractSet<T> {

  @Override
  public Iterator<T> iterator() {
    return null;
  }

  @Override
  public int size() {
    return 0;
  }

  public void method2(T arg) {
    System.out.println(arg.toString());
  }
}
