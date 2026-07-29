import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import figures.*;

public class Eve {
	public static void main(String[] args) {
	MeuFrame frame = new MeuFrame();
	}
}

class MeuFrame extends JFrame {
	Rect r1;
	Ellipse e1;

	public MeuFrame() {
		this.addWindowListener (
			new WindowAdapter() {
				public void windowClosing (WindowEvent e) {
					System.exit(0);
				}
			}
		);

	this.setTitle("Eve - editor vetorial");
	this.setSize(500, 500);
	this.setVisible(true);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.blue);
		int w = getWidth();
		int h = getHeight();

		// cor de fundo
		g2d.setPaint(Color.black);
		g2d.fillRect(0,0, w,h);
	}
}
