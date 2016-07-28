package tech.toparvion.jmint.model;

import org.slf4j.LoggerFactory;
import tech.toparvion.jmint.modify.*;

/**
 * Created by Toparvion on 29.04.2016 11:59
 */
public enum CutpointType {
  BEFORE(BeforeBodyModifier.class),
  INSTEAD(InsteadBodyModifier.class),
  AFTER(AfterBodyModifier.class),
  CATCH(CatchBodyModifier.class),
  /**
   * This synthetic cutpoint type is introduced to denote a method that must not be instrumented. This however doesn't
   * mean that the method must not be included into targets map. It is still included there to avoid breaking the
   * order of tree walking.
   */
  IGNORE(MethodModifier.class /* <- the modifier is just a stub as this cutpoint is never actually applied */);

  /**
   * The cutpoint which gets selected if none of cutpoints matches the given name.
   */
  public static final CutpointType DEFAULT_CUTPOINT_TYPE = IGNORE;

  private final Class<? extends MethodModifier> modifierClass;

  CutpointType(Class<? extends MethodModifier> modifierClass) {
    this.modifierClass = modifierClass;
  }

  public MethodModifier getModifier() throws IllegalAccessException, InstantiationException {
    return modifierClass.newInstance();
  }

  /**
   * Finds the enum element matching given name or fails back to {@code CutpointType#DEFAULT_CUTPOINT_TYPE} if none can be found.
   * @param name the name to find matching cutpoint for
   * @return cutpoint with the same name or {@linkplain CutpointType#DEFAULT_CUTPOINT_TYPE} if not found
   */
  public static CutpointType getByNameSafe(String name) {
    if (name == null) return DEFAULT_CUTPOINT_TYPE;
    for (CutpointType cutpointType : values()) {
      if (cutpointType.toString().equalsIgnoreCase(name)) {
        return cutpointType;
      }
    }
    LoggerFactory.getLogger(CutpointType.class).debug("Unknown cutpoint type encountered: {}", name);
    return DEFAULT_CUTPOINT_TYPE;
  }

}
