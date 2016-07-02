package ru.ftc.upc.testing.dropper;

import javassist.*;
import ru.ftc.upc.testing.dropper.model.Argument;
import ru.ftc.upc.testing.dropper.model.TargetMethod;
import ru.ftc.upc.testing.dropper.model.TargetsMap;
import ru.ftc.upc.testing.dropper.modify.MethodModifier;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.*;

import static java.lang.String.format;
import static ru.ftc.upc.testing.dropper.Cutpoint.IGNORE;

/**
 * Created by Toparvion on 29.04.2016 12:50
 */
class PatchingTransformer implements ClassFileTransformer {
  private ClassPool pool;
  private final TargetsMap targetsMap;
  private final Set<ClassLoader> knownLoaders = new HashSet<ClassLoader>();
  private final Set<String> knownPackages = new HashSet<String>();

  PatchingTransformer(TargetsMap targetsMap) {
    this.targetsMap = targetsMap;
    this.pool = ClassPool.getDefault();
  }

  @Override
  public byte[] transform(ClassLoader loader,
                          String className,
                          Class<?> classBeingRedefined,
                          ProtectionDomain protectionDomain,
                          byte[] classFileBuffer) throws IllegalClassFormatException {
    // first of all let's check if we're interested in this class
    if (!targetsMap.containsKey(className)) {
      return null;
    }
    // this class is of interest, so let's try to modify it
    try {
      if (knownLoaders.add(loader)) {           // TODO avoid explicit loaders comparison
        ClassPool childPool = new ClassPool(pool);
        childPool.childFirstLookup = true;
        pool = childPool;
      }

      // locate the class in the pool, taking the classFileBuffer as a source
      Deque<TargetMethod> targetMethods = targetsMap.get(className);
      String dottedClassName = className.replace("/", ".");
      pool.insertClassPath(new ByteArrayClassPath(dottedClassName, classFileBuffer));
      CtClass ctClass = pool.get(dottedClassName);

      // iterate over desired methods trying to modify each one
      for (TargetMethod targetMethod : targetMethods) {
        try {
          if (IGNORE.equals(targetMethod.getCutpoint())) {
            System.out.printf("Method '%s#%s' is skipped due to IGNORE cutpoint.\n", dottedClassName, targetMethod.getName());
            continue;
          }
          // in order to precisely select method to modify we need CtClass representation of all its parameters
          CtClass[] argsArray = javassistifyArguments(targetMethod.getFormalParams(), targetMethod.getImportsOnDemand());

          // pick javassist representation of target method
          CtBehavior ctMethod = (targetMethod.getResultType() != null)        // constructors do not have result type
                  ? ctClass.getDeclaredMethod(targetMethod.getName(), argsArray)
                  : ctClass.getDeclaredConstructor(argsArray);

          // prepare javassist compiler to resolve not fully qualified types
          for (String importEntry : targetMethod.getImportsOnDemand()) {
            if (knownPackages.add(importEntry)) {
              pool.importPackage(importEntry);
            }
          }

          // apply modification via corresponding cutpoint
          Cutpoint cutpoint = targetMethod.getCutpoint();
          MethodModifier modifier = cutpoint.modifierClass.newInstance();
          modifier.apply(ctMethod, targetMethod.getText());
          System.out.printf("Method '%s' has been modified at %s cutpoint.\n", ctMethod.getLongName(), cutpoint);

        } catch (Exception e) {
          System.out.printf("Failed to modify target method '%s#%s'. Skipped.\n", dottedClassName, targetMethod.getName());
          e.printStackTrace();
        }
      }
      return ctClass.toBytecode();

    } catch (Exception e) {
      System.out.println(format("Failed to modify class '%s'. Skipped.", className));
      e.printStackTrace();
      return null;
    }
  }

  private CtClass[] javassistifyArguments(List<Argument> args, Set<String> importsOnDemand) throws NotFoundException {
    List<CtClass> argCtClasses = new ArrayList<CtClass>(args.size());
    for (Argument arg : args) {
      String argumentTypeName = arg.getType();
      CtClass argumentCtClass = pool.getOrNull(argumentTypeName);
      // if this arg has not been found in the pool, let's try to prepend its name with various imports on demand
      if (argumentCtClass == null) {
        for (String importEntry : importsOnDemand) {
          argumentCtClass = pool.getOrNull(importEntry + "." + arg.getType());
          if (argumentCtClass != null) {
            break;
          }
        }
        if (argumentCtClass == null) {
          throw new NotFoundException(format("Couldn't resolve argument '%s %s'.", arg.getType(), arg.getName()));
        }
      }
      argCtClasses.add(argumentCtClass);
    }
    return argCtClasses.toArray(new CtClass[args.size()]);
  }

}
