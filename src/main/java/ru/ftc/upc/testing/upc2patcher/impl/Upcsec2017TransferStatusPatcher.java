package ru.ftc.upc.testing.upc2patcher.impl;

import javassist.CtClass;
import javassist.CtMethod;
import ru.ftc.upc.testing.upc2patcher.ClassPatcher;

import static java.lang.String.format;

/**
 * Created by Plizga on 28.04.2016 14:41
 */
public class Upcsec2017TransferStatusPatcher implements ClassPatcher {

  private static final String PATIENT_CLASS_NAME = "models/TransferStatus";
  private static final String PATIENT_METHOD_NAME = "isFinite";

  @Override
  public boolean accept(String className) {
    return PATIENT_CLASS_NAME.equals(className);
  }

  @Override
  public byte[] patch(CtClass class2patch) throws Exception {
    CtMethod patient = class2patch.getDeclaredMethod(PATIENT_METHOD_NAME);
    patient.setBody("return false;");
    System.out.println(format("Method '%s' has been patched.", patient.getLongName()));
    return class2patch.toBytecode();
  }
}
