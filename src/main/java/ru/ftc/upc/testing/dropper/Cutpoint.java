package ru.ftc.upc.testing.dropper;

import ru.ftc.upc.testing.dropper.modify.AfterBodyModifier;
import ru.ftc.upc.testing.dropper.modify.BeforeBodyModifier;
import ru.ftc.upc.testing.dropper.modify.InsteadBodyModifier;
import ru.ftc.upc.testing.dropper.modify.MethodModifier;

/**
 * Created by Toparvion on 29.04.2016 11:59
 */
public enum Cutpoint {
  BEFORE(BeforeBodyModifier.class),
  INSTEAD(InsteadBodyModifier.class),
  AFTER(AfterBodyModifier.class),
  CATCH(MethodModifier.class),                // TODO implement dedicated modifier
  /**
   * This synthetic cutpoint is introduced to denote a method that must not be instrumented. This however doesn't
   * mean that the method must not be included into targets map. It is still included there to avoid breaking the
   * order of tree walking.
   */
  IGNORE(MethodModifier.class /* <- the modifier is just a stub as this cutpoint is never actually applied */);

  final Class<? extends MethodModifier> modifierClass;

  Cutpoint(Class<? extends MethodModifier> modifierClass) {
    this.modifierClass = modifierClass;
  }

  /**
   * Finds the enum element matching given name or fails back to {@code INSTEAD} if none can be found.
   * @param name the name to find matching cutpoint for
   * @return cutpoint with the same name or {@code INSTEAD} if not found
   */
  public static Cutpoint getByNameSafe(String name) {
    if (name == null) return INSTEAD;
    for (Cutpoint cutpoint : values()) {
      if (cutpoint.toString().equalsIgnoreCase(name)) {
        return cutpoint;
      }
    }
    return INSTEAD;
  }

}
