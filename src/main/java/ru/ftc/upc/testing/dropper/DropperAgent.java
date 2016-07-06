package ru.ftc.upc.testing.dropper;

import ru.ftc.upc.testing.dropper.model.TargetsMap;

import java.lang.instrument.Instrumentation;

/**
 * Created by Toparvion on 28.04.2016 9:13
 */
@SuppressWarnings({"unused", "WeakerAccess"}) // the class is used by Java Instrumentation API and thus must be public
public class DropperAgent {

  public static void premain(String agentArgs, Instrumentation inst) {
    System.out.println(DROPPER_LOGO);

    Package pack = DropperAgent.class.getPackage();
    System.out.printf("%s started (version: %s).\n", pack.getImplementationTitle(), pack.getImplementationVersion());

    long startTime = System.currentTimeMillis();
    TargetsMap targetsMap = DropletLoader.loadDroplets(agentArgs);
    System.out.printf("Loaded targets map:\n%s\n", targetsMap.toString());
    System.out.printf("Droplets loading took: %s ms\n", (System.currentTimeMillis()-startTime));

    if (targetsMap.isEmpty()) {
      System.out.printf("No droplets to apply left after arguments processing. No byte code will be modified.\n");
    } else {
      inst.addTransformer(new PatchingTransformer(targetsMap));
    }
  }

  //<editor-fold desc="Logo">
  private static final String DROPPER_LOGO =
    "     _____                                     \n" +
    "    (____ \\                                    \n" +
    "     _   \\ \\ ____ ___  ____  ____   ____  ____ \n" +
    "    | |   | / ___) _ \\|  _ \\|  _ \\ / _  )/ ___)\n" +
    "    | |__/ / |  | |_| | | | | | | ( (/ /| |    \n" +
    "    |_____/|_|   \\___/| ||_/| ||_/ \\____)_|    \n" +
    "                      |_|   |_|                ";
  //</editor-fold>
}
