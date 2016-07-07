package tech.toparvion.jmint.lang.samples;

/**
 * @author Toparvion
 */
@SuppressWarnings("unused")
public enum RootEnumeration {

  ONE,
  TWO,
  THREE;

  RootEnumeration() {
    String nothing = "I'm the most enumerated constructor ever!";
  }

  public static RootEnumeration getByName(String name) {
    for (RootEnumeration rootEnum : values()) {
      if (rootEnum.toString().equals(name)) {
        return rootEnum;
      }
    }
    throw new IllegalArgumentException("Not found: " + name);
  }

  private enum InnerEnum {
    INNER_ENUM;

    public boolean isTheSame(Enum<RootEnumeration> e) {
      return INNER_ENUM.toString().equals(e.toString());
    }
  }

}
