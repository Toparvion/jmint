/**
 * 		Главный модуль программы; отвечает за инициализацию (в любом режиме)
 * 		и построение/заполнение области программы.
 */
package sampleapp.standalone.painter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainterDroplet extends JPanel {

  private JTextField statusField;
  private JTabbedPane tabbedPane;
  final private String[] names = {"Пинта", "Нинья", "Мария", "Санта", "Доминика"};
  final private int points[] = {80, 98, 130, 137, 200};


  /**
   * @param args
   * @cutpoint BEFORE
   */
  public static void main(String[] args) {
    System.out.println("Length of arguments list: " + args.length);
  }

  /**
   * @cutpoint AFTER
   */
  private void buildContent() {
    statusField.setText(statusField.getText() + " [Droplet is active ;) ]");
  }


  /*   private JPanel createBottomPane()
     {
         JPanel thePane = new JPanel();
         thePane.setBorder(BorderFactory.createTitledBorder("Управление фигурами"));
         GridBagLayout gbl = new GridBagLayout();
         thePane.setLayout(gbl);
         GridBagConstraints gbc = new GridBagConstraints();
        
         gbc.weightx = 1;
         gbc.weighty = 0;
         gbc.anchor = GridBagConstraints.WEST;
         // заголовок "таблицы"
         JLabel next = new JLabel("     Состояние");
         thePane.add(next, gbc);
         next = new JLabel("Сложность");
         thePane.add(next, gbc);
         next = new JLabel("Время расчета");
         gbc.gridwidth = GridBagConstraints.REMAINDER;
         thePane.add(next,gbc);
         // тело "таблицы"
         int i;
         JCheckBox turner;
         JLabel pointCnt;
         JProgressBar prgs;
         for(i=0; i<names.length; i++)
         {
             turner = new JCheckBox(names[i] + " активна");
             gbc.gridy = i+1;
             gbc.gridwidth = 1;
             gbc.fill = GridBagConstraints.NONE;
             thePane.add(turner, gbc);
             pointCnt = new JLabel("[поток не запущен]");
             pointCnt.setEnabled(false);
             thePane.add(pointCnt, gbc);
             prgs = new JProgressBar(90000, 270000);
             prgs.setStringPainted(true);
             prgs.setVisible(false);
             gbc.gridwidth = GridBagConstraints.REMAINDER;
             gbc.fill = GridBagConstraints.BOTH;
             thePane.add(prgs, gbc);
             turner.addActionListener(new ActivateListener(pointCnt, prgs, names[i], points[i]));

         }

         return thePane;

     }

 */
  class ActivateListener implements ActionListener {

    /**
     * @cutpoint AFTER AS_FINALLY
     */
    public void actionPerformed(ActionEvent event) {
      JCheckBox src = (JCheckBox) event.getSource();
      boolean set = src.isSelected();
      System.out.println(set ? "Tab is added." : "Tab is excluded.");
    }
  }
}
