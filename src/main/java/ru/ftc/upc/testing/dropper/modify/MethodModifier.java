package ru.ftc.upc.testing.dropper.modify;

import javassist.CannotCompileException;
import javassist.CtBehavior;

/**
 * Created by Toparvion on 29.04.2016 14:38
 */
public interface MethodModifier {

  void apply(CtBehavior method, String injection) throws CannotCompileException;
}
