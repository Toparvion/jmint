package ru.ftc.upc.testing.dropper;

import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ftc.upc.testing.dropper.modify.MethodModifier;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;

/**
 * Created by Toparvion on 29.04.2016 12:50
 */
class PatchingTransformer implements ClassFileTransformer {
  private static final Logger log = LoggerFactory.getLogger(PatchingTransformer.class);

  private final Map<String, Droplet> dropletMap = new HashMap<String, Droplet>();
  private final ClassPool pool;

  PatchingTransformer(Set<Droplet> droplets) {
    pool = ClassPool.getDefault();

    for (Droplet droplet : droplets) {
      String jvmClazzFqName = droplet.getClazz().replaceAll("\\.", "/");
      dropletMap.put(jvmClazzFqName, droplet);
    }
  }

  @Override
  public byte[] transform(ClassLoader loader,
                          String className,
                          Class<?> classBeingRedefined,
                          ProtectionDomain protectionDomain,
                          byte[] classFileBuffer) throws IllegalClassFormatException {
    if (!dropletMap.containsKey(className)) {
      return null;
    }
    try {
      return applyDroplet(dropletMap.get(className), classFileBuffer);

    } catch (Exception e) {
      log.error(format("Failed to modify class '%s'. Class skipped.", className), e);
      return null;
    }
  }

  private byte[] applyDroplet(Droplet droplet, byte[] classFileBuffer) throws Exception {
    String className = droplet.getClazz();
    pool.insertClassPath(new ByteArrayClassPath(className, classFileBuffer));
    CtClass ctClass = pool.get(className);
    if (ctClass.isFrozen())
      throw new IllegalStateException(format("Class '%s' is frozen.", ctClass.getName()));

    CtMethod ctMethod = ctClass.getDeclaredMethod(droplet.getMethod());

    Cutpoint cutpoint = droplet.getCutpoint();
    MethodModifier patcher = cutpoint.patcherClass.newInstance();

    patcher.apply(ctMethod, droplet);
    log.info("Class {} has been modified with {}.", ctClass.getName(), patcher.getClass().getSimpleName());
    return ctClass.toBytecode();
  }
}
