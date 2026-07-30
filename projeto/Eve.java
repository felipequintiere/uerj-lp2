import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;

import figures.*;

public class Eve {
	public static void main(String[] args) {
	MeuFrame frame = new MeuFrame();
	}
}

class MeuFrame extends JFrame {
	Rect r1;
	Ellipse e1;

	ArrayList<Rect> rs = new ArrayList<Rect>();
	Random rand = new Random();  // ADD THIS

	public MeuFrame() {
		this.addWindowListener (
			new WindowAdapter() {
				public void windowClosing (WindowEvent e) {
					System.exit(0);
				}
			}
		);

		this.setTitle("Eve - Editor Vetorial");
		this.setSize(500, 500);
		this.setVisible(true);

		// event listeners
		this.addKeyListener(
			new KeyAdapter() {
				public void keyPressed(KeyEvent evt) {
					if (evt.getKeyChar() == 'r') {
						int x = rand.nextInt(w/2);
						int y = rand.nextInt(w/2);
						int w = rand.nextInt(w/2);
						int h = rand.nextInt(w/2);
						rs.add(new Rect(x,y, w,h));
						repaint();
					}
				}
			}
		);
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
