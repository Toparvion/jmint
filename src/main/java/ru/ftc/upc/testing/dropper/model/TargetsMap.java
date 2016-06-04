package ru.ftc.upc.testing.dropper.model;

import java.util.*;

/**
 * @author Toparvion
 */
public class TargetsMap extends LinkedHashMap<String, Deque<TargetMethod>> {

  public void put(String key, TargetMethod method) {
    Deque<TargetMethod> methods = this.get(key);
    if (methods == null) {
      methods = new LinkedList<TargetMethod>();
      methods.addLast(method);
      this.put(key, methods);

    } else {
      checkForDuplicate(methods, method, key);      // throws IllegalArgumentException if a duplicate found
      methods.addLast(method);
    }
  }

  private void checkForDuplicate(Deque<TargetMethod> methods, TargetMethod newMethod, String classFqName)
          throws IllegalArgumentException {
    for (TargetMethod oldMethod : methods) {
      if (oldMethod.getName().equals(newMethod.getName())
              && oldMethod.getCutpoint().equals(newMethod.getCutpoint())
              && areParameterListsEqual(oldMethod, newMethod)) {
        String message = String.format("Method '%s' of class '%s' is already added for modifying in cutpoint '%s'.",
                newMethod.getName(), classFqName, newMethod.getCutpoint());
        throw new IllegalArgumentException(message);
      }
    }
  }

  private boolean areParameterListsEqual(TargetMethod method1, TargetMethod method2) {
    if (method1.getFormalParams().size() != method2.getFormalParams().size()) {
      return false;
    }
    List<Argument> params1 = method1.getFormalParams();
    List<Argument> params2 = method2.getFormalParams();
    for (int i = 0; i < params1.size(); i++) {
      String type1 = params1.get(i).getType();
      String type2 = params2.get(i).getType();
      if (!type1.equals(type2)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, Deque<TargetMethod>> entry : this.entrySet()) {
      sb.append(entry.getKey()).append(" -> {\n");
      for (TargetMethod method : entry.getValue()) {
        sb.append("\t").append(method).append("\n");
      }
      sb.append("}\n");
    }
    return sb.toString();
  }
}
