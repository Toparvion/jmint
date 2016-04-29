package ru.ftc.upc.testing.patcher;

/**
 * Created by Plizga on 29.04.2016 11:59
 */
@SuppressWarnings("unused")       // все элементы перечисления на самом деле используются через метод Enum#valueOf()
enum Cutpoint {
  BEFORE(BeforeMethodPatcher.class),
  INSTEAD(InsteadMethodPatcher.class),
  AFTER(AfterMethodPatcher.class);

  final Class<? extends MethodPatcher> patcherClass;

  Cutpoint(Class<? extends MethodPatcher> patcherClass) {
    this.patcherClass = patcherClass;
  }

}
