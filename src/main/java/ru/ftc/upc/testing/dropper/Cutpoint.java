package ru.ftc.upc.testing.dropper;

import ru.ftc.upc.testing.dropper.modify.AfterBodyModifier;
import ru.ftc.upc.testing.dropper.modify.BeforeBodyModifier;
import ru.ftc.upc.testing.dropper.modify.InsteadBodyModifier;
import ru.ftc.upc.testing.dropper.modify.MethodModifier;

/**
 * Created by Toparvion on 29.04.2016 11:59
 */
@SuppressWarnings("unused")       // all the elements of the Enum is actually used through Enum#valueOf()
public enum Cutpoint {
  BEFORE(BeforeBodyModifier.class),
  INSTEAD(InsteadBodyModifier.class),
  AFTER(AfterBodyModifier.class),
  CATCH(MethodModifier.class);           // TODO implement dedicated patcher

  final Class<? extends MethodModifier> patcherClass;

  Cutpoint(Class<? extends MethodModifier> patcherClass) {
    this.patcherClass = patcherClass;
  }

}
