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
	Button msgButton = new Button(450, 300, 150, 100);
	public static TextArea txtArea = new TextArea(0, 300, 400, 200);
	private Server s;
	private Client c;

	public Surface() {
		b.setBorderColor(Color.CYAN);
		b.setCornerRadius(10);
		b.setText("Create Server");
		b.setTextColor(Color.CYAN);
		b.setTextAlignment(Textalign.mittig);
		b.setTextFontSize(34);
		
		txtArea.setBackgroundColor(Color.BLACK);
		txtArea.setTextColor(Color.CYAN);	
		
		b2.setBorderColor(Color.CYAN);
		b2.setCornerRadius(10);
		b2.setText("Connect");
		b2.setTextColor(Color.CYAN);
		b2.setTextAlignment(Textalign.mittig);
		b2.setTextFontSize(34);
		
		msgButton.setBorderColor(Color.CYAN);
		msgButton.setCornerRadius(10);
		msgButton.setText("send msg");
		msgButton.setTextColor(Color.CYAN);
		msgButton.setTextAlignment(Textalign.mittig);
		msgButton.setTextFontSize(34);
	}

	public void checkButtonPress(int x, int y) {
		if (b.contains(x, y)) {
			s = new Server();
		}
		if (b2.contains(x, y)) {
			c = new Client();
		}
		if (msgButton.contains(x, y)) {
			c.sendMSG();
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
		msgButton.paint(g);
	}
}
