/**
 *
 */
package lab3;

import javax.swing.*;
import java.util.*;

/**
 * @author Toparvion
 */
public class DrawPaneDroplet extends JComponent implements Runnable {

  /**
   *
   * @param indicator
   * @param points
   * @param parent
   * @cutpoint BEFORE
   */
  public DrawPaneDroplet(JProgressBar indicator, int points, Painter parent) {
    System.out.println("UUID: " + UUID.randomUUID().toString());
    System.out.println("Parent component size is " + parent.getSize().toString());
  }

  /**
   * @cutpoint IGNORE
   */
  public void run() {
    long times = 0;
    times++;
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
