package tech.toparvion.jmint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import tech.toparvion.jmint.lang.DropletLoader;
import tech.toparvion.jmint.model.TargetsMap;

import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.util.logging.LogManager;

import static java.lang.String.format;

/**
 * Created by Toparvion on 28.04.2016 9:13
 */
@SuppressWarnings({"unused", "WeakerAccess"}) // the class is used by Java Instrumentation API and thus must be public
public class JMintAgent {

  static {
    InputStream stream = JMintAgent.class.getClassLoader().getResourceAsStream("logging.properties");
    try {
      LogManager.getLogManager().readConfiguration(stream);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void premain(String agentArgs, Instrumentation inst) {
    System.out.println(JMINT_LOGO);
    Log log = LogFactory.getLog(JMintAgent.class);

    Package pack = JMintAgent.class.getPackage();
    log.info(format("%s started (version: %s).", pack.getImplementationTitle(), pack.getImplementationVersion()));

    long startTime = System.currentTimeMillis();
    TargetsMap targetsMap = DropletLoader.loadDroplets(agentArgs);
    log.info(format("Loaded targets map:\n%s", targetsMap.toString()));
    log.info(format("Droplets loading took: %d ms", (System.currentTimeMillis()-startTime)));

    if (targetsMap.isEmpty()) {
      log.warn("No droplets to apply left after arguments processing. No byte code will be modified.");
    } else {
      inst.addTransformer(new DropletsInjector(targetsMap));
    }
  }

  //<editor-fold desc="Logo">
  private static final String JMINT_LOGO =
        "     _   ____    ____     _               _    \n" +
        "    (_) |_   \\  /   _|  (_)             / |_  \n" +
        "    __    |   \\/   |    __    _ .--.   `| |-' \n" +
        "   [  |   | |\\  /| |   [  |  [ `.-. |   | |   \n" +
        " _  | |  _| |_\\/_| |_   | |   | | | |   | |,  \n" +
        "[ \\_| | |_____||_____| [___] [___||__]  \\__/  \n" +
        " \\____/                                       ";
  //</editor-fold>
}
