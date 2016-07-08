package tech.toparvion.jmint.lang.samples;

import java.io.InputStream;
import java.util.AbstractSet;
import java.util.Iterator;

/**
 * @author Toparvion
 */
@SuppressWarnings("unused")
public class GenericClass<T> extends AbstractSet<T> {

  @Override
  public Iterator<T> iterator() {
    return null;
  }

  @Override
  public int size() {
    return 0;
  }

  public <R extends InputStream> void method2(R arg) {
    System.out.println(arg.toString());
  }
}
