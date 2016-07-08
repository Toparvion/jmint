/**
 * 
 */
package sampleapp.standalone.painter;

import javax.swing.*;
import java.awt.*;

public class PainterApplet extends JApplet {
	public void init()
	{
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(new Painter(this), BorderLayout.CENTER);
	}
}
