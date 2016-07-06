/**
 * 		Главный модуль программы; отвечает за инициализацию (в любом режиме)
 * 		и построение/заполнение области программы.
 */
package sampleapp.standalone.painter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Painter extends JPanel {

	private PainterApplet applet = null;
	private JFrame frame = null;
	private JTextField statusField;
	private JTabbedPane tabbedPane;
	private JPanel settingsPanel;
	private JPanel topPane;
//	private JPanel bottomPane;
	private JMenuBar menuBar;

    final private String[] names = {"Пинта", "Нинья", "Мария", "Санта", "Доминика"};
    final private int points[] = {80, 98, 130, 137, 200};
    final private int dkm_def = 64;
    final private int rb_def = 115;
    final private int rm_def = 25;

    private double bDelta = 0.0;
    private int dkm = dkm_def;
    private int rb  = rb_def;
    private int rm  = rm_def;

	/**
	 * @param applet объект апплета
	 */
	public Painter(PainterApplet applet) {
		this.applet = applet;
		if (applet == null) {
			frame = new JFrame("Лабораторная работа №4");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		// set the layout
        this.setPreferredSize(new Dimension(500, 420));
        setLayout(new BorderLayout());

		buildContent();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showFrame();
			}
		});
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Painter painter = new Painter(null);

	}
	
	public synchronized int getDkm() {
        return dkm;
    }

    public synchronized int getRb() {
        return rb;
    }

    public synchronized int getRm() {
        return rm;
    }

    public synchronized double getBDelta() {
        return bDelta;
    }

    public synchronized void setBDelta(double bDelta) {
        this.bDelta = bDelta;
    }

    private void buildContent() {
	
		tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
        add(tabbedPane, BorderLayout.CENTER);

		// создание и расположение Панели Настроек
		settingsPanel = new JPanel();
		settingsPanel.setLayout(new BorderLayout());
		
		topPane = new JPanel();
		topPane.setLayout(new BorderLayout());
		topPane.setBorder(BorderFactory.createTitledBorder("Свойства задания"));
        JLabel welcome = new JLabel("<html>В этой программе параметры построения влияют на <i>все</i> оси и " +
                "относятся ко <i>всем</i> активным фигурам.</html>");
        topPane.add(welcome, BorderLayout.CENTER);
        settingsPanel.add(topPane, BorderLayout.NORTH);

        JPanel paramPane = createParamPane();
        settingsPanel.add(paramPane, BorderLayout.WEST);
        JPanel optionPane = createOptionPane();
        settingsPanel.add(optionPane, BorderLayout.EAST);

        JPanel bottomPane = this.createBottomPane();
        settingsPanel.add(bottomPane, BorderLayout.SOUTH);

        tabbedPane.addTab("Настройки", settingsPanel);

        menuBar = createMenu();
	    if (isApplet()) {
	        applet.setJMenuBar(menuBar);
	    } else {
	        frame.setJMenuBar(menuBar);
	    }			
		
		statusField = new JTextField("Добро пожаловать!");
		statusField.setEditable(false);
        tabbedPane.getModel().addChangeListener(new TabListener());// имеет непосредственное отношение к строке состояния
        add(statusField, BorderLayout.SOUTH);

    }

    private JPanel createParamPane()
    {
        JPanel pane = new JPanel();
        JSpinner spinner;
        pane.setBorder(BorderFactory.createTitledBorder("Параметры построения"));
		pane.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 10;
		gbc.ipady = 4;
		gbc.insets = new Insets(5,5,5,5);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		pane.add(new JLabel("Коэффициент завихрения:"), gbc);
        spinner = new JSpinner(new SpinnerNumberModel(dkm_def, 0, 128, 1));
        spinner.addChangeListener(new ParamListener("dkm"));
        pane.add(spinner, gbc);
		gbc.gridy = 1;
        // переход к следующей "строке" таблицы размещения
		pane.add(new JLabel("Радиус завихрения:"), gbc);
        spinner = new JSpinner(new SpinnerNumberModel(rm_def, 0 , 120, 1));
        spinner.addChangeListener(new ParamListener("rm"));
		pane.add(spinner, gbc);
		gbc.gridy = 2;
		pane.add(new JLabel("Основной радиус:"), gbc);
        spinner = new JSpinner(new SpinnerNumberModel(rb_def, 0, 260, 1));
        spinner.addChangeListener(new ParamListener("rb"));
		pane.add(spinner, gbc);

        return pane;
    }

    private JPanel createOptionPane()
    {
        JPanel pane = new JPanel();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 10;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        JRadioButton option;
        pane.setBorder(BorderFactory.createTitledBorder("Вращение основы"));

        ButtonGroup optionGroup = new ButtonGroup();
        option = new JRadioButton("Отсутствует", true);
        option.addActionListener(new OptionListener(0));
        optionGroup.add(option);
        pane.add(option, gbc);

        option = new JRadioButton("По часовой стрелке", false);
        option.addActionListener(new OptionListener(5));
        optionGroup.add(option);
        pane.add(option, gbc);

        option = new JRadioButton("Против часовой стрелки       ", false);
        option.addActionListener(new OptionListener(-5));
        optionGroup.add(option);
        pane.add(option, gbc);

        return pane;

    }

    private JPanel createBottomPane()
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

    private JPanel createTab(JProgressBar prgs, final JLabel pointCnt, int points)
    {
        JPanel showTab = new JPanel();
		showTab.setLayout(new BorderLayout());

	    final DrawPane drawPane = new DrawPane(prgs, points, this);
		drawPane.setPreferredSize(new Dimension(300, 300));
		showTab.add(drawPane, BorderLayout.CENTER);

		JPanel bottomPane = new JPanel();
		bottomPane.setLayout(new BorderLayout());
		bottomPane.setBorder(BorderFactory.createTitledBorder("Количество точек построения"));

        JSlider nSlide = new JSlider(80, 200, points);
		nSlide.setPaintTicks(true);
		nSlide.setMajorTickSpacing(10);
		nSlide.setMinorTickSpacing(1);
		nSlide.setPaintLabels( true );
		nSlide.setSnapToTicks( true );
		nSlide.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
    	        JSlider s1 = (JSlider)e.getSource();
                int newValue = s1.getValue();
                drawPane.setN(newValue);
                pointCnt.setText(String.valueOf(newValue) + " точек");

            }
        });
        bottomPane.add(nSlide, BorderLayout.CENTER);
        showTab.add(bottomPane, BorderLayout.SOUTH);
//		animCheck = new JCheckBox("Анимация");
//		animCheck.addChangeListener(new ChangeListener() {
//			public void stateChanged(ChangeEvent e)
//			{
//				JCheckBox doAnim = (JCheckBox)e.getSource();
//				drawPane.setDoAnim(doAnim.isSelected());
//			}
//		});
//		showTab.add(animCheck, BorderLayout.SOUTH);

        showTab.putClientProperty("drawPane", drawPane);

        return showTab;

    }

    private JMenuBar createMenu()
	{
		JMenuBar bar = new JMenuBar();		
		JMenu menuX = new JMenu("Главное");

        JMenuItem quit = new JMenuItem("Выход");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        });
        if (isApplet())
            quit.setEnabled(false);
        menuX.add(quit);

        bar.add(menuX);

		return bar;
	}

	public void showFrame() {
		if (!isApplet() && frame != null) {
			// put swingset in a frame and show it 
			frame.getContentPane().add(this, BorderLayout.CENTER);
			frame.setLocation(100, 100);
            frame.pack();
			frame.setVisible(true);
		}
	}

	public boolean isApplet() {
		return (applet != null);
	}

    class SliderListener implements ChangeListener {
    	DrawPane dp;
    	public SliderListener(DrawPane pane) {
    	    dp = pane;
    	}
    	public void stateChanged(ChangeEvent e) {
    	    JSlider s1 = (JSlider)e.getSource();
    	    dp.setN(s1.getValue());
    	}
    }
    

    class TabListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            SingleSelectionModel model = (SingleSelectionModel) e.getSource();
            int tabSelected = model.getSelectedIndex();
            if (tabSelected == 0)
                statusField.setText("Настройте параметры фигуры и перейдите на вкладку Просмотр");
            if (tabSelected > 0)
                statusField.setText("Используйте колесо мыши и кнопку под ним для управления анимацией");

        }
    }

    class ActivateListener implements ActionListener {
        private JLabel pointCnt;
        private JProgressBar prgs;
        private String name;
        private int points;

        private JPanel child = null;

        public ActivateListener(JLabel pointCnt, JProgressBar prgs, String name, int points)
        {
            this.pointCnt = pointCnt;
            this.prgs = prgs;
            this.name = name;
            this.points = points;
        }
        public void actionPerformed(ActionEvent event) {
            JCheckBox src = (JCheckBox)event.getSource();
            boolean set = src.isSelected();
            if (set)                    // флажок установлен
            {
                if (child==null)
                    child = createTab(prgs, pointCnt, points);
                tabbedPane.addTab(name, child);

            }
            else                        // флажок снят
            {
                if (child!=null)
                {
                    tabbedPane.remove(child);
                    DrawPane drawPane = (DrawPane)child.getClientProperty("drawPane");
                    drawPane.dispose();
                    child = null;
                }

            }
            prgs.setVisible(set);
            pointCnt.setEnabled(set);
            pointCnt.setText(set ? (points+" точек"):"[поток не запущен]");

        }

    }

    class OptionListener implements ActionListener
    {
        int delta;

        public OptionListener(int delta)
        {
            this.delta = delta;
        }

        public void actionPerformed(ActionEvent event)
        {
            setBDelta((double)delta/1000.0);
        }

    }

    class ParamListener implements ChangeListener
    {
        private String param;

        public ParamListener(String param)
        {
            this.param = param;
        }

        public void stateChanged(ChangeEvent event)
        {
			JSpinner src = (JSpinner) event.getSource();
			SpinnerNumberModel snm = (SpinnerNumberModel) src.getModel();
			int newValue = snm.getNumber().intValue();
            if (param == "dkm")
                dkm = newValue;
            if (param == "rb")
                rb = newValue;
            if (param == "rm")
                rm = newValue;


        }
    }

}
