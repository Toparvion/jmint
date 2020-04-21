package tech.toparvion.jmint;

import tech.toparvion.jmint.lang.DropletLoader;
import tech.toparvion.jmint.model.TargetsMap;

import java.lang.instrument.Instrumentation;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;

/**
 * Created by Toparvion on 28.04.2016 9:13
 */
@SuppressWarnings({"unused", "WeakerAccess"}) // the class is used by Java Instrumentation API and thus must be public
public class JMintAgent {
  private static final Logger log;
  static {
    System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %3$s - %5$s%6$s%n");
    log = Logger.getLogger(JMintAgent.class.getName());
  }

  public static void premain(String agentArgs, Instrumentation inst) {
    System.out.println(JMINT_LOGO);

    Package pack = JMintAgent.class.getPackage();
    log.log(INFO, format("%s started (version: %s).", pack.getImplementationTitle(), pack.getImplementationVersion()));

    long startTime = System.currentTimeMillis();
    TargetsMap targetsMap = DropletLoader.loadDroplets(agentArgs);
    log.log(INFO, format("Loaded targets map:\n%s", targetsMap.toString()));
    log.log(INFO, format("Droplets loading took: %s ms", (System.currentTimeMillis()-startTime)));

    if (targetsMap.isEmpty()) {
      log.log(WARNING, "No droplets to apply left after arguments processing. No byte code will be modified.");
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
