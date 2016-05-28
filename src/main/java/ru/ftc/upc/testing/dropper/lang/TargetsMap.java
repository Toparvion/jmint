package ru.ftc.upc.testing.dropper.lang;

import ru.ftc.upc.testing.dropper.model.TargetMethod;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Toparvion
 */
public class TargetsMap extends TreeMap<String, List<TargetMethod>> {

  public void appendMethod(String key, TargetMethod method) {
    List<TargetMethod> methods = this.get(key);
    if (methods == null) {
      methods = new LinkedList<TargetMethod>();
      methods.add(method);
      this.put(key, methods);

    } else {
      methods.add(method);
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, List<TargetMethod>> entry : this.entrySet()) {
      sb.append(entry.getKey()).append(" -> {\n");
      for (TargetMethod method : entry.getValue()) {
        sb.append("\t").append(method).append("\n");
      }
      sb.append("}\n");
    }
    return sb.toString();
  }
}
