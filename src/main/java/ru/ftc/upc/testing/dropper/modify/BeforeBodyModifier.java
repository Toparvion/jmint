package ru.ftc.upc.testing.dropper.modify;

import javassist.CannotCompileException;
import javassist.CtMethod;
import ru.ftc.upc.testing.dropper.Droplet;

/**
 * Created by Toparvion on 29.04.2016 14:44
 */
public class BeforeBodyModifier implements MethodModifier {

  @Override
  public void apply(CtMethod method, Droplet droplet) throws CannotCompileException {
    method.insertBefore(droplet.getText());
  }
}
