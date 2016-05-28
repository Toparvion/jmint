package ru.ftc.upc.testing.dropper.model;

/**
 * @author Toparvion
 */
public class Argument {
  private final String type;
  private final String name;

  public Argument(String type, String name) {
    this.type = type;
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Argument{" +
            "type='" + type + '\'' +
            ", name='" + name + '\'' +
            '}';
  }
}
