package tech.toparvion.jmint.modify;

import javassist.CannotCompileException;
import javassist.CtBehavior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

/**
 * Created by Toparvion on 29.04.2016 14:51
 */
public class AfterBodyModifier implements MethodModifier {
  private static final Logger log = LoggerFactory.getLogger(AfterBodyModifier.class);
  private static final Pattern AS_FINALLY_PATTERN = Pattern.compile("as[-_ ]?finally", CASE_INSENSITIVE);

  @Override
  public void apply(String injection, CtBehavior method, String... auxParams) throws CannotCompileException {
    boolean asFinally = false;
    if (auxParams != null && auxParams.length > 0) {
      String paramValue = auxParams[0];
      if (auxParams.length > 1) {
        paramValue = paramValue + auxParams[1];
      }
      asFinally = AS_FINALLY_PATTERN.matcher(paramValue.trim()).matches();
    }
    log.info("AFTER cutpoint for method '{}' {} be applied as finally block.", method.getName(),
            asFinally ? "will" : "won't");
    method.insertAfter(injection, asFinally);
  }
}
