package ru.ftc.upc.testing.upc2patcher;

import javassist.CtClass;

/**
 * Created by Plizga on 28.04.2016 12:25
 */
public interface ClassPatcher {
  boolean accept(String className);
  byte[] patch(CtClass class2patch) throws Exception;
}
