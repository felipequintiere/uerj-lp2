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
	ArrayList<Figure> figs = new ArrayList<Figure>();

	// ponteiro para o objeto em foco
	Figure focus = null;

	int mouseX;
	int mouseY;

	public MeuFrame() {
		this.setTitle("Eve - Editor Vetorial");
		this.setSize(700, 700);
		this.setVisible(true);

		// fechar a janela
		this.addWindowListener (
			new WindowAdapter() {
				public void windowClosing (WindowEvent e) {
					System.exit(0);
				}
			}
		);

		// posição atual do ponteiro
		this.addMouseMotionListener(
			new MouseMotionAdapter() {
				public void mouseMoved(MouseEvent evt) {
					mouseX = evt.getX();
					mouseY = evt.getY();
				}
				public void mouseDragged(MouseEvent evt) {
					if (focus == null) {
						return;
					}

					int dx, dy;
					dx = evt.getX() - mouseX;
					dy = evt.getY() - mouseY;
					mouseX = evt.getX();
					mouseY = evt.getY();
					
					System.out.printf("dx: %d dy: %d\n", dx,dy);

					focus.drag(dx,dy);
					repaint();
				}
			}
		);

		// comandos atrelados às teclas
		this.addKeyListener(
			new KeyAdapter() {
				public void keyPressed(KeyEvent evt) {

					Random rand = new Random();
					Color fillColorAleatorio = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
					Color borderColorAleatorio = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));

					if (evt.getKeyChar() == 'r') {
						figs.add(new Rect(mouseX, mouseY, 100, 60, fillColorAleatorio, borderColorAleatorio));
					}
					else if (evt.getKeyChar() == 'e') {
						figs.add(new Ellipse(mouseX, mouseY, 100, 60, fillColorAleatorio, borderColorAleatorio));
					}
					else if (evt.getKeyChar() == 'l') {
						figs.add(new Line(mouseX, mouseY, mouseX+100, mouseY+60, borderColorAleatorio));
					}
					else if (evt.getKeyCode() == KeyEvent.VK_DELETE ||
							evt.getKeyCode() == '\b' ) {
						if (focus != null) {
							figs.remove(focus);
							focus = null;
						}
					}
					repaint();
				}
			}
		);

		this.addMouseListener
			(new MouseAdapter() {
				public void mousePressed(MouseEvent evt) {
					focus = null;

					for (Figure fig : figs) {
						if (fig.contains(evt.getX(), evt.getY())) {
							focus = fig;
						}
					}

					// DEBUG
					// printar no terminal a figura selecionada
					System.out.println(focus);
				}
			}
		);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		int w = getWidth();
		int h = getHeight();


		// cor de fundo
		//g2d.setPaint(Color.black);
		//g2d.fillRect(0,0, w,h);

		for (Figure fig : figs) {
			fig.paint(g2d);

			if (fig == focus) {
				fig.changeBorder(); // quero destacar a figura selecionada
			}
		}
	}
}
