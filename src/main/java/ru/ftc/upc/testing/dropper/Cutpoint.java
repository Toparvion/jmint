package ru.ftc.upc.testing.dropper;

/**
 * Created by Plizga on 29.04.2016 11:59
 */
@SuppressWarnings("unused")       // all the elements of the Enum is actually used through Enum#valueOf()
public enum Cutpoint {
  BEFORE(BeforeMethodPatcher.class),
  INSTEAD(InsteadMethodPatcher.class),
  AFTER(AfterMethodPatcher.class),
  CATCH(MethodPatcher.class);           // TODO implement dedicated patcher

  final Class<? extends MethodPatcher> patcherClass;

  Cutpoint(Class<? extends MethodPatcher> patcherClass) {
    this.patcherClass = patcherClass;
  }

}
