package ru.ftc.upc.testing.dropper.model;

import ru.ftc.upc.testing.dropper.Cutpoint;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Toparvion
 */
public class TargetMethod {
  private String name;
  private Cutpoint cutpoint = Cutpoint.INSTEAD;     // by default cutpoint is INSTEAD
  private String text;
  private String resultType;
  private List<Argument> formalParams = new LinkedList<Argument>();

  public TargetMethod(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Cutpoint getCutpoint() {
    return cutpoint;
  }

  public void setCutpoint(Cutpoint cutpoint) {
    this.cutpoint = cutpoint;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public List<Argument> getFormalParams() {
    return formalParams;
  }

  public String getResultType() {
    return resultType;
  }

  public void setResultType(String resultType) {
    this.resultType = resultType;
  }

  /**
   * <em>ATTENTION!</em> This method is actively used in unit tests result checking, therefore its changing may cause
   * many tests fail.
   * @return string representation of this target method
   */
  @Override
  public String toString() {
    return "TargetMethod{" +
            "name='" + name + '\'' +
            ", cutpoint=" + cutpoint +
            ", resultType=" + resultType +
            ", formalParams=" + listParamsToString(formalParams) +
            ", text=" + (text == null || text.isEmpty() ? "(empty)" : "\n\t\t"+text+"\n\t") +
            '}';
  }

  private String listParamsToString(List<Argument> formalParams) {
    StringBuilder sb = new StringBuilder("(");
    boolean isFirst = true;
    for (Argument argument: formalParams) {
      if (!isFirst) {
        sb.append(", ");
      } else {
        isFirst = false;
      }
      sb.append(argument.getType())
              .append(" ")
              .append(argument.getName());
    }
    sb.append(")");
    return sb.toString();
  }

}
