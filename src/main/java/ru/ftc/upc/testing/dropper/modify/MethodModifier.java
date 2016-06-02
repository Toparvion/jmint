package ru.ftc.upc.testing.dropper.modify;

import javassist.CannotCompileException;
import javassist.CtMethod;
import ru.ftc.upc.testing.dropper.Droplet;

/**
 * Created by Toparvion on 29.04.2016 14:38
 */
public interface MethodModifier {

  void apply(CtMethod method, Droplet droplet) throws CannotCompileException;
}
