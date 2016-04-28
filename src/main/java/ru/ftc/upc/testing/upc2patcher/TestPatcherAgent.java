package ru.ftc.upc.testing.upc2patcher;

import java.lang.instrument.Instrumentation;

/**
 * Created by Plizga on 28.04.2016 9:13
 */
@SuppressWarnings("unused")
public class TestPatcherAgent {
  public static void premain(String agentArgs, Instrumentation inst) {
    System.out.println("UPC2 Patcher Agent started.");
    try {
      inst.addTransformer(new PatchingTransformer());

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
