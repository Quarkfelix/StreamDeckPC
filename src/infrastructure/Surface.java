package infrastructure;

import java.awt.Color;
import java.awt.Graphics2D;

import libary.Button;
import libary.TextArea;
import libary.Textalign;
import server.Client;
import server.Server;

public class Surface {
	Button b = new Button(100, 100, 200, 150);
	Button b2 = new Button(450, 100, 200, 150);
	public static TextArea txtArea = new TextArea(100, 300, 600, 200);
	private Server s;
	private Client c;

	public Surface() {
		b.setBorderColor(Color.CYAN);
		b.setCornerRadius(10);

		b.setText("Create Server");
		b.setTextColor(Color.CYAN);
		b.setTextAlignment(Textalign.mittig);
		b.setTextFontSize(34);
		
		b2.setBorderColor(Color.CYAN);
		b2.setCornerRadius(10);

		b2.setText("Connect");
		b2.setTextColor(Color.CYAN);
		b2.setTextAlignment(Textalign.mittig);
		b2.setTextFontSize(34);

		
		txtArea.setBackgroundColor(Color.BLACK);
		txtArea.setTextColor(Color.CYAN);
	}

	public void checkButtonPress(int x, int y) {
		if (b.contains(x, y)) {
			s = new Server();
		}
		if (b2.contains(x, y)) {
			c = new Client();
		}
	}
	
	public static void outprint(String txt) {
		txtArea.setText(txt);
	}

//paint--------------------------------------------------------------------------------------------------------
	public void paint(Graphics2D g) {
		b.paint(g);
		b2.paint(g);
		txtArea.paint(g);
	}
}
