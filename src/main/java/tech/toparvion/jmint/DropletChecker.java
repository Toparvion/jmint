package tech.toparvion.jmint;

import tech.toparvion.jmint.lang.DropletLoader;
import tech.toparvion.jmint.model.TargetsMap;

/**
 * @author Toparvion
 */
public class DropletChecker {

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.printf(USAGE_HELP);
      return;
    }

    TargetsMap targetsMap = DropletLoader.loadDroplets(args[0]);
    System.out.printf("Loaded targets map:\n%s", targetsMap.toString());

    System.out.println("\nListed droplet(s) format is OK.");
  }

  private static final String USAGE_HELP = "Usage: java -jar jmint.jar <droplet-list>\n" +
          "   Argument <droplet-list> is a semicolon-separated list of droplet files to check.\n" +
          "   The list is exactly the" +
          " same as one that should be given to dropper in usual (java agent) mode after '=' sign,\n" +
          "   i.e. -javaagent:jmint.jar=BusinessClassDroplet.java;SystemClassDroplet.java.\nExample:\n" +
          "   java -jar jmint.jar BusinessClassDroplet.java;SystemClassDroplet.java";
}
