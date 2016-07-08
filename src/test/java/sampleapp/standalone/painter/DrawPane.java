/**
 *
 */
package sampleapp.standalone.painter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawPane extends JComponent implements Runnable {

  private int n;
  private double t = 0.0;
  private double dt = 0.0004;

  private float red;
  private float green;
  private float blue;

  private JProgressBar indicator;
  private Painter parent;

  int xs[] = new int[200];            // !!
  int ys[] = new int[200];            // !!

  // just a test
  private double t2 = 0.0001;

  public int dkmx = 64, dkmy = 64;
  public int rmx = 25, rmy = 25;
  public int rbx = 115, rby = 115;

  private Thread anim;
  private boolean doAnim = true;
  private boolean runnable = true;
  private MouseWheelListener ml;
  private MouseListener mb;

  public DrawPane(JProgressBar indicator, int points, Painter parent) {
    this.indicator = indicator;
    this.n = points;
    this.parent = parent;

    ml = new MouseAdapter() {                      // обработка вращения колесика мыши
      public void mouseWheelMoved(MouseWheelEvent e) {
        int change = e.getWheelRotation();
        //System.out.println("Mouse wheel has been rounded for " + change);
        dt -= (double) change / 50000;

      }
    };
    this.addMouseWheelListener(ml);          // назначение слушателя

    mb = new MouseAdapter() {                  // обработчик кнопок мыши
      public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON2)  // колесо
          dt = 0.0005;
      }
    };
    this.addMouseListener(mb);                // назначение слушателя

    anim = new Thread(this, "Shape Animation");
    anim.start();
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
//        calculate();
    g2d.setBackground(Color.WHITE);
    g2d.clearRect(0, 0, this.getWidth(), this.getHeight());              // удаление предыдущей фигуры
    red = ((float) Math.sin(t * dkmx) + 1) / 2;
    green = (float) (Math.sin(t + n) + 1) / 2;
    blue = (float) (Math.cos(t + n) + 1) / 2;
    g2d.setColor(new Color(red, green, blue));              // назначение цвета
    g2d.drawPolygon(xs, ys, n);                                  // построение фигуры

  }

  private void calculate() {
    int i;
    double r, x, y;
    Dimension dm = getSize();
    dkmx = dkmy = parent.getDkm();
    rbx = rby = parent.getRb();
    rmx = rmy = parent.getRm();
    t2 += parent.getBDelta();
    double round = 2 * Math.PI;
    for (i = 0; i < n; i++) {
      r = (double) i / (double) n * round + t; // +t
      x = Math.cos(r + t2) * rbx + Math.sin(r * dkmx) * rmx + dm.width / 2;
      y = Math.sin(r + t2) * rby + Math.cos(r * dkmy) * rmy + dm.height / 2;
      xs[i] = (int) x;
      ys[i] = (int) y;

    }
  }


  /**
   * @param n the n to set
   */
  public void setN(int n) {
    this.n = n;
  }

  /**
   * @return the n
   */
  public int getN() {
    return n;
  }

  /**
   * @param doAnim the doAnim to set
   */
  public void setDoAnim(boolean doAnim) {
    this.doAnim = doAnim;
  }

  public void run() {
    long times = 0;
    while (runnable) {
      t += doAnim ? dt : 0.0;
//            t2 -= 0.0001;
      long startTime = System.nanoTime();
      calculate();
      long stopTime = System.nanoTime();
      repaint();
      if (times % 80 == 0) {
        int time = (int) (stopTime - startTime);
        indicator.setValue(time);
        indicator.setString(String.valueOf(time) + " нс");
      }

      times++;
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public void dispose() {
    this.removeMouseListener(mb);
    this.removeMouseWheelListener(ml);
    runnable = false;
  }
}
