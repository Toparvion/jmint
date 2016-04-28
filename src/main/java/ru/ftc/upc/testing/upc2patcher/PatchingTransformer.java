package ru.ftc.upc.testing.upc2patcher;

import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import org.reflections.Reflections;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;

/**
 * Created by Plizga on 28.04.2016 9:23
 */
public class PatchingTransformer implements ClassFileTransformer {
  private static final Set<ClassPatcher> patchers = new HashSet<ClassPatcher>();

  private ClassPool pool;

  public PatchingTransformer() throws IllegalAccessException, InstantiationException {
    pool = ClassPool.getDefault();

    Reflections reflections = new Reflections("ru.ftc.upc.testing.upc2patcher.impl");
    Set<Class<? extends ClassPatcher>> patcherClasses = reflections.getSubTypesOf(ClassPatcher.class);
    for (Class<? extends ClassPatcher> patcherClass : patcherClasses) {
      patchers.add(patcherClass.newInstance());
    }
  }

  @Override
  public byte[] transform(ClassLoader loader,
                          String className,
                          Class<?> classBeingRedefined,
                          ProtectionDomain protectionDomain,
                          byte[] classfileBuffer) throws IllegalClassFormatException {
    for (ClassPatcher patcher : patchers) {
      if (patcher.accept(className)) {
        String dottedClassName = className.replaceAll("/", ".");
        System.out.println(format("Class '%s' has been accepted by patcher '%s'.", dottedClassName, patcher.getClass().getSimpleName()));
        return apply(patcher, dottedClassName, classfileBuffer);
      }
    }
    return null;
   }

  private byte[] apply(ClassPatcher patcher, String dottedClassName, byte[] classfileBuffer) {
    try {
      pool.insertClassPath(new ByteArrayClassPath(dottedClassName, classfileBuffer));
      CtClass ctClass = pool.get(dottedClassName);
      if (ctClass.isFrozen()) {
        throw new IllegalStateException(format("Class '%s' is frozen.", ctClass.getName()));
      }
      return patcher.patch(ctClass);

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
