package ru.ftc.upc.testing.upc2patcher.impl;

import javassist.CtClass;
import javassist.CtMethod;
import ru.ftc.upc.testing.upc2patcher.ClassPatcher;

import static java.lang.String.format;

/**
 * Created by Plizga on 28.04.2016 14:54
 */
public class Upcsec2017SMSSenderImplPatcher implements ClassPatcher {

  private static final String PATIENT_CLASS_NAME = "sms/SMSSenderImpl";
  private static final String PATIENT_METHOD_NAME = "sendMessage";

  @Override
  public boolean accept(String className) {
    return PATIENT_CLASS_NAME.equals(className);
  }

  @Override
  public byte[] patch(CtClass class2patch) throws Exception {
    CtMethod patient = class2patch.getDeclaredMethod(PATIENT_METHOD_NAME);
    patient.insertBefore("log.warn(\"SMS message to be sent: '{}'\", $2);");
    System.out.println(format("Method '%s' has been patched.", patient.getLongName()));
    return class2patch.toBytecode();
  }
}
