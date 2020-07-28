package infrastructure;

import javax.swing.JFrame;

import io.KeyHandler;
import io.MouseHandler;

public class GUI extends JFrame{
	private JFrame jf;
	public static Draw draw;
	
	public GUI() {
		jf = new JFrame();
		draw = new Draw();
		
		jf.setSize(800,600);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jf.addKeyListener(new KeyHandler());
		jf.addMouseListener(new MouseHandler());
		jf.add(draw);
		jf.requestFocus();
		jf.setVisible(true);
	}
}
