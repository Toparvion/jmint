package javax.swing;

class JFrameDroplet {

  /**
   * @cutpoint BEFORE
   * @param title
   */
  public JFrameDroplet(String title) {
    if (title.contains("работа")) {
      title = title + " (Droplet mode)";
    }
  }
}