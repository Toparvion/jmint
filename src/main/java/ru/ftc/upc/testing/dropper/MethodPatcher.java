package ru.ftc.upc.testing.dropper;

import javassist.CannotCompileException;
import javassist.CtMethod;

/**
 * Created by Plizga on 29.04.2016 14:38
 */
interface MethodPatcher {

  void apply(CtMethod method, Droplet droplet) throws CannotCompileException;
}
