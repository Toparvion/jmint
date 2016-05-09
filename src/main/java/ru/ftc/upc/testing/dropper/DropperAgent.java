package ru.ftc.upc.testing.dropper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

/**
 * Created by Plizga on 28.04.2016 9:13
 */
@SuppressWarnings("unused")
public class DropperAgent {
  private static final Logger log = LoggerFactory.getLogger("Dropper");

  private static final String DROPLET_LINE_PREFIX = "//droplet";

  public static void premain(String agentArgs, Instrumentation inst) {
    System.out.println(DROPPER_LOGO);

    Package pack = DropperAgent.class.getPackage();
    log.info("{} started (version: {}).", pack.getImplementationTitle(), pack.getImplementationVersion());

    Set<Droplet> droplets = getDroplets(agentArgs);
    for (Droplet droplet : droplets) {
      log.info("Loaded droplet: {}", droplet);
    }

    if (droplets.isEmpty()) {
      log.warn("No droplets to inject left after arguments processing.");
    } else {
      log.info("Total {} droplets loaded.", droplets.size());
      inst.addTransformer(new PatchingTransformer(droplets));
    }
  }

  private static Set<Droplet> getDroplets(String args) {
    if (args == null || "".equals(args)) throw new IllegalArgumentException("No arguments supplied.");

    Set<Droplet> droplets = new HashSet<Droplet>();
    String[] argTokens = args.split(";");
    for (String token : argTokens) {
      try {
        File dropletFile = new File(token);
        if (!dropletFile.canRead()) {
          log.warn("Droplet '{}' can not be read by the agent.", token);
          continue;
        }

        Droplet droplet = loadDroplet(dropletFile);
        droplets.add(droplet);

      } catch (Exception e) {
        log.error(format("Failed to load droplet '%s'.", token), e);
      }
    }
    return droplets;
  }

  private static Droplet loadDroplet(File dropletFile) throws Exception {
    FileReader fileReader = new FileReader(dropletFile);
    BufferedReader bufferedReader = new BufferedReader(fileReader);

    // заготовка дроплета для заполнения
    Droplet droplet = new Droplet();

    String line;
    // основной обход файла дроплета
    while ((line = bufferedReader.readLine()) != null) {
      if (!line.startsWith(DROPLET_LINE_PREFIX)) {
        continue;
      }
      String entryValue;

      // проверка на класс
      entryValue = getEntryValue(line, "class");
      if (entryValue != null) {
        droplet.setClazz(entryValue);
        continue;
      }

      // проверка на метод
      entryValue = getEntryValue(line, "method");
      if (entryValue != null) {
        droplet.setMethod(entryValue);
        continue;
      }

      // проверка на точку подключения
      entryValue = getEntryValue(line, "cutpoint");
      if (entryValue != null) {
        Cutpoint cutpoint = Cutpoint.valueOf(entryValue);
        droplet.setCutpoint(cutpoint);
        continue;
      }

      // проверка на текст дроплета
      entryValue = getEntryValue(line, "text");
      if (entryValue != null) {
        String text = loadDropletText(bufferedReader);
        droplet.setText(text);
        break;      // текст предполагается в конце дроплета, поэтому читать дальше смысла уже нет
      }
    }
    bufferedReader.close();

    // убедимся, что все "запчасти" дроплета были считаны
    String missedField = droplet.getMissedField();
    if (missedField != null) throw new IllegalArgumentException(format("Droplet doesn't contain field '%s' or it " +
            "is not correctly formatted.", missedField));

    return droplet;
  }

  private static String getEntryValue(String line, String entryKey) {
    Pattern pattern = Pattern.compile(entryKey + ":[\\x20\\t]*(\\p{Graph}*)", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(line);
    if (matcher.find()) {
      return matcher.group(1);
    } else {
      return null;
    }
  }

  private static String loadDropletText(BufferedReader bufferedReader) throws IOException {
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = bufferedReader.readLine()) != null) {
      sb.append(line).append("\n");
    }
    return sb.toString();
  }

  //<editor-fold desc="Logo">
  private static final String DROPPER_LOGO =
          "██████╗ ██████╗  ██████╗ ██████╗ ██████╗ ███████╗██████╗ \n" +
          "██╔══██╗██╔══██╗██╔═══██╗██╔══██╗██╔══██╗██╔════╝██╔══██╗\n" +
          "██║  ██║██████╔╝██║   ██║██████╔╝██████╔╝█████╗  ██████╔╝\n" +
          "██║  ██║██╔══██╗██║   ██║██╔═══╝ ██╔═══╝ ██╔══╝  ██╔══██╗\n" +
          "██████╔╝██║  ██║╚██████╔╝██║     ██║     ███████╗██║  ██║\n" +
          "╚═════╝ ╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚═╝     ╚══════╝╚═╝  ╚═╝\n";
  //</editor-fold>
}
