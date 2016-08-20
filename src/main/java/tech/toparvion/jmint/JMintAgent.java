package tech.toparvion.jmint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.toparvion.jmint.lang.DropletLoader;
import tech.toparvion.jmint.model.TargetsMap;

import java.lang.instrument.Instrumentation;

/**
 * Created by Toparvion on 28.04.2016 9:13
 */
@SuppressWarnings({"unused", "WeakerAccess"}) // the class is used by Java Instrumentation API and thus must be public
public class JMintAgent {
  private static final Logger log = LoggerFactory.getLogger(JMintAgent.class);

  public static void premain(String agentArgs, Instrumentation inst) {
    System.out.println(JMINT_LOGO);

    Package pack = JMintAgent.class.getPackage();
    log.info("{} started (version: {}).", pack.getImplementationTitle(), pack.getImplementationVersion());

    long startTime = System.currentTimeMillis();
    TargetsMap targetsMap = DropletLoader.loadDroplets(agentArgs);
    log.info("Loaded targets map:\n{}", targetsMap.toString());
    log.info("Droplets loading took: {} ms", (System.currentTimeMillis()-startTime));

    if (targetsMap.isEmpty()) {
      log.warn("No droplets to apply left after arguments processing. No byte code will be modified.");
    } else {
      inst.addTransformer(new DropletsInjector(targetsMap));
    }
  }

  //<editor-fold desc="Logo">
  private static final String JMINT_LOGO =
        "     _   ____    ____    _               _    \n" +
        "    (_) |_   \\  /   _|  (_)             / |_  \n" +
        "    __    |   \\/   |    __    _ .--.   `| |-' \n" +
        "   [  |   | |\\  /| |   [  |  [ `.-. |   | |   \n" +
        " _  | |  _| |_\\/_| |_   | |   | | | |   | |,  \n" +
        "[ \\_| | |_____||_____| [___] [___||__]  \\__/  \n" +
        " \\____/                                       ";
  //</editor-fold>
}
