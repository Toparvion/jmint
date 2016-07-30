package tech.toparvion.jmint.model;

import java.util.Arrays;

import static tech.toparvion.jmint.model.CutpointType.DEFAULT_CUTPOINT_TYPE;

/**
 * @author Toparvion
 */
public class Cutpoint {
  /**
   * The cutpoint that will be chosen in case no alternatives were found.
   */
  public static final Cutpoint DEFAULT_CUTPOINT = new Cutpoint(DEFAULT_CUTPOINT_TYPE);
  private CutpointType type;
  private String[] auxParams = null;

  public Cutpoint(CutpointType type) {
    this.type = type;
  }

  public CutpointType getType() {
    return type;
  }

  public String[] getAuxParams() {
    return auxParams;
  }

  public void setAuxParams(String[] auxParams) {
    this.auxParams = auxParams;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Cutpoint cutpoint = (Cutpoint) o;

    if (type != cutpoint.type) return false;
    // Probably incorrect - comparing Object[] arrays with Arrays.equals
    return Arrays.equals(auxParams, cutpoint.auxParams);
  }

  @Override
  public String toString() {
    return "Cutpoint{" +
            "type=" + type +
            ((auxParams != null) ? ", auxParams=" + Arrays.toString(auxParams) : "") +
            '}';
  }
}
