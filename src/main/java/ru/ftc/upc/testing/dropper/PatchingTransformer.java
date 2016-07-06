package ru.ftc.upc.testing.dropper;

import javassist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  private static final Logger log = LoggerFactory.getLogger(PatchingTransformer.class);

  /**
   * The package that is implicitly imported into every Javassist ClassPool instance and therefore should be considered
   * known while resolving formal parameters' FQ names.
   */
  private static final String DEFAULT_PACKAGE = "java.lang";

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
      // but before, let's store the class loader if it is a new one for us
      if ((loader != null)
              && !loader.getClass().getName().contains("sun.misc.DelegatingClassLoader")
              && knownLoaders.add(loader)) {
        ClassPool childPool = new ClassPool(pool);
        childPool.insertClassPath(new LoaderClassPath(loader));
        childPool.childFirstLookup = true;
        pool = childPool;
      }

      // locate the class in the pool, taking the classFileBuffer as a source
      Deque<TargetMethod> targetMethods = targetsMap.get(className);
      String dottedClassName = className.replace("/", ".");
      pool.insertClassPath(new ByteArrayClassPath(dottedClassName, classFileBuffer));
      CtClass ctClass = pool.get(dottedClassName);

      // in order to include the containing package into CtClass search scope we explicitly extract it from class name
      String localPackage = dottedClassName.contains(".")
              ? dottedClassName.substring(0, dottedClassName.lastIndexOf('.'))
              : null;
      // iterate over desired methods trying to modify each one
      for (TargetMethod targetMethod : targetMethods) {
        try {
          if (IGNORE.equals(targetMethod.getCutpoint())) {
            log.info("Method '{}#{}' is skipped due to IGNORE cutpoint.", dottedClassName, targetMethod.getName());
            continue;
          }
          // in order to precisely select method to modify we need CtClass representation of all its formal parameters
          CtClass[] argsArray = javassistifyArguments(
                  targetMethod.getFormalParams(),
                  targetMethod.getImportsOnDemand(),
                  localPackage);

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
          log.info("Method '{}' has been modified at {} cutpoint.", ctMethod.getLongName(), cutpoint);

        } catch (Exception e) {
          log.error(format("Failed to modify target method '%s#%s'. Skipped.",
                  dottedClassName, targetMethod.getName()), e);
        }
      }
      return ctClass.toBytecode();

    } catch (Exception e) {
      log.error(format("Failed to modify class '%s'. Skipped.", className), e);
      return null;
    }
  }

  /**
   * Initializes Javassist CtClass representations for all given arguments. This is required for precise selecting of
   * the method to modify - Javassist must know all its formal parameters as CtClass objects.
   * @param args arguments of target method
   * @param importsOnDemand a set of imports on demand (needed during searching for CtClass objects)
   * @param localPackage full name of a package containing target class (used in param names resolving)
   * @return an array of CtClass objects representing all given arguments
   * @throws NotFoundException if some of arguments can not be represented as CtClass object
   */
  private CtClass[] javassistifyArguments(List<Argument> args, Set<String> importsOnDemand, String localPackage)
          throws NotFoundException {
    // the actual import set is an extension to the given one: it also includes default and local packages
    Set<String> extendedImports = new HashSet<String>(importsOnDemand);
    extendedImports.add(DEFAULT_PACKAGE);
    if (localPackage != null)
      extendedImports.add(localPackage);

    List<CtClass> argCtClasses = new ArrayList<CtClass>(args.size());
    for (Argument arg : args) {
      // the first, 'naive' attempt is taken to resolve a parameter of primitive type
      String argumentTypeName = arg.getType();
      CtClass argumentCtClass = pool.getOrNull(argumentTypeName);
      // if it fails, let's try to prepend param name with various imports on demand (including implicit ones)
      if (argumentCtClass == null) {
        for (String importEntry : extendedImports) {
          String supposedFqName = importEntry + "." + arg.getType();
          argumentCtClass = pool.getOrNull(supposedFqName);
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
