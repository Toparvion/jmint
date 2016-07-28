package tech.toparvion.jmint.modify;

import javassist.CannotCompileException;
import javassist.CtBehavior;

/**
 * @author Toparvion
 */
public class CatchBodyModifier implements MethodModifier {

  @Override
  public void apply(String injection, CtBehavior method, String... auxParams) throws CannotCompileException {
    throw new RuntimeException("CATCH cutpoint is not supported yet. Please post an issue on " +
            "https://github.com/Toparvion/jmint to require the support in one of upcoming versions.");
  }
}
