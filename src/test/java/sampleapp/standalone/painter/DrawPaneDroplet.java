/**
 *
 */
package sampleapp.standalone.painter;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

/**
 * @author Toparvion
 */
public class DrawPaneDroplet extends JComponent /*implements Runnable*/ {

  public int dkmx, dkmy;
  private int n;
  private double t;
  int xs[];
  int ys[];
  private float red;
  private float green;
  private float blue;

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
   *
   * @param g
   * @cutpoint INSTEAD
   */
  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
//        calculate();
    g2d.setBackground(Color.BLACK);
    g2d.clearRect(0, 0, this.getWidth(), this.getHeight());              // удаление предыдущей фигуры
    red = ((float) Math.sin(t * dkmx) + 1) / 2;
    green = (float) (Math.sin(t + n) + 1) / 2;
    blue = (float) (Math.cos(t + n) + 1) / 2;
    g2d.setColor(new Color(red, green, blue));              // назначение цвета
    g2d.drawPolygon(xs, ys, n);                                  // построение фигуры
  }
}
