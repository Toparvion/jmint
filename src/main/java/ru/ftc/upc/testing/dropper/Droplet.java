package ru.ftc.upc.testing.dropper;

/**
 * Created by Plizga on 29.04.2016 11:58
 */
class Droplet {
  private String clazz;
  private String method;
  private Cutpoint cutpoint;
  private String text;

  public String getClazz() {
    return clazz;
  }

  public void setClazz(String clazz) {
    this.clazz = clazz;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
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

  public String getMissedField() {
    if (clazz == null) return "class";
    if (method == null) return "method";
    if (cutpoint == null) return "cutpoint";
    if (text == null) return "text";
    return null;
  }

  @Override
  public String toString() {
    return "Droplet{" +
            "clazz='" + clazz + '\'' +
            ", method='" + method + '\'' +
            ", cutpoint=" + cutpoint +
            "}; text=\n" + text;
  }
}
