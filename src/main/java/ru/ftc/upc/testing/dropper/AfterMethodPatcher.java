package ru.ftc.upc.testing.dropper;

import javassist.CannotCompileException;
import javassist.CtMethod;

/**
 * Created by Plizga on 29.04.2016 14:51
 */
class AfterMethodPatcher implements MethodPatcher {

  @Override
  public void apply(CtMethod method, Droplet droplet) throws CannotCompileException {
    method.insertAfter(droplet.getText());
  }
}
