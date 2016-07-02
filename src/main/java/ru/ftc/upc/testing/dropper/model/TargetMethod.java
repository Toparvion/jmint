package ru.ftc.upc.testing.dropper.model;

import ru.ftc.upc.testing.dropper.Cutpoint;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Toparvion
 */
public class TargetMethod {
  private final String name;
  private final Set<String> importsOnDemand;
  private final Cutpoint cutpoint;

  private String text;
  private String resultType;
  private List<Argument> formalParams = new LinkedList<Argument>();

  public TargetMethod(String name, Cutpoint cutpoint, Set<String> importsOnDemand) {
    this.name = name;
    this.cutpoint = cutpoint;
    this.importsOnDemand = importsOnDemand;
  }

  public String getName() {
    return name;
  }

  public Cutpoint getCutpoint() {
    return cutpoint;
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

  public Set<String> getImportsOnDemand() {
    return importsOnDemand;
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
            (importsOnDemand.size()<2 ? "" : ",\n\t\timportsOnDemand="+listImportsToString(importsOnDemand)) +
            ", text=" + (text == null || text.isEmpty() ? "(empty)" : "\n\t\t"+text+"\n\t") +
            '}';
  }

  private static String listParamsToString(List<Argument> formalParams) {
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

  private static String listImportsToString(Set<String> imports) {
    StringBuilder sb = new StringBuilder("(");
    boolean isFirst = true;
    for (String anImport : imports) {
      if (!isFirst) {
        sb.append(", ");
      } else {
        isFirst = false;
      }
      sb.append(anImport);
    }
    sb.append(")");
    return sb.toString();

  }

}
