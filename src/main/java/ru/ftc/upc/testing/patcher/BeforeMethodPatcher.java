package ru.ftc.upc.testing.patcher;

import javassist.CannotCompileException;
import javassist.CtMethod;

/**
 * Created by Plizga on 29.04.2016 14:44
 */
class BeforeMethodPatcher implements MethodPatcher {

  @Override
  public void apply(CtMethod method, Droplet droplet) throws CannotCompileException {
    method.insertBefore(droplet.getText());
  }
}
