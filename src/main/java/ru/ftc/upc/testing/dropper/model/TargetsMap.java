package ru.ftc.upc.testing.dropper.model;

import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

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
      methods.addLast(method);
    }
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
