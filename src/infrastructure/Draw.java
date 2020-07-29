package infrastructure;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Draw extends JPanel {
	public static Surface surface;

	// constructor------------------------------------------------------------------------------------------------------------
	public Draw() {
		surface = new Surface();
	}

	// paint------------------------------------------------------------------------------------------------------------
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		try {
			surface.paint(g);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
