package ru.ftc.upc.testing.dropper.model;

/**
 * @author Toparvion
 */
public class DropletV2 {

  private final String path;
  private final TargetsMap targetsMap;

  public DropletV2(String path, TargetsMap targetsMap) {
    this.path = path;
    this.targetsMap = targetsMap;
  }

  public String getPath() {
    return path;
  }

  public TargetsMap getTargetsMap() {
    return targetsMap;
  }
}
