import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;

import figures.*;

public class Eve {
	public static void main(String[] args) {
		EveFrame frame = new EveFrame();
	}
}

class EveFrame extends JFrame {
	ArrayList<Figure> figs = new ArrayList<Figure>();

	// focus/hover sobre objeto
	Figure focus = null;
	Figure hover = null;

	boolean resizing = false;

	int mouseX;
	int mouseY;
	int dx;
	int dy;

	int index; // index da lista heterogênea

	public EveFrame() {
		this.setTitle("Eve - Editor Vetorial");
		this.setSize(700, 700);
		this.setVisible(true);

		// fechar a janela
		this.addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			}
		);

		this.addMouseMotionListener(
			new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					if (focus == null) {
						return;
					}

					dx = e.getX() - mouseX;
					dy = e.getY() - mouseY;

					mouseX = e.getX();
					mouseY = e.getY();

					if (resizing) {
						focus.resize(dx,dy);
					}
					else {
						focus.drag(dx,dy);
					}
					repaint();

				}

				public void mouseMoved(MouseEvent e) {
					mouseX = e.getX();
					mouseY = e.getY();

					Figure aux_hover = hover;
					boolean foundHover = false;

					for (Figure fig : figs) {
						if (fig.contains(e.getX(), e.getY())) {
							hover = fig;
							foundHover = true;
						}
					}

					if (aux_hover != hover) {
						repaint();
					}
					// remover a borda azul quando o ponteiro não estiver
					// sobre um objeto
					else if (aux_hover != null && !foundHover) {
						hover = null;
						repaint();
					}
				}
			}
		);
		this.addMouseListener(
			new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						if (focus != null &&
								focus.resizeContains(e.getX(), e.getY())) {
							resizing = true;
						}
						else if (hover == null) {
							focus = null;
						}
						else if (hover.contains(e.getX(), e.getY())) {
							focus = hover;
						}
					}
					else if (e.getButton() == MouseEvent.BUTTON3) {
						if (hover == null) {
							focus = null;
						}
						else if (hover.contains(e.getX(), e.getY())) {
							focus = hover;
							resizing = true;
						}
					}

					repaint();
				}
				public void mouseReleased(MouseEvent e) {
					resizing = false;
				}
			}
		);
		this.addMouseWheelListener(
			new MouseWheelListener() {
				public void mouseWheelMoved(MouseWheelEvent e) {
					if (focus == null) {
						return;
					}

					// negative values if the mouse wheel was
					// rotated up or away from the user, and
					// positive values if the mouse wheel was
					// rotated down or towards the user
					focus.rotate(e.getWheelRotation() / 20.0);
					System.out.printf("%f\n", focus.getAngle());
					repaint();
				}
			}
		);

		this.addKeyListener(
			new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.isControlDown()) {
						switch(e.getKeyCode()) {
						case KeyEvent.VK_U:
							index = figs.indexOf(focus);

							if (index < figs.size() - 1) {
								Figure tmp = figs.get(index);
								figs.set(index, figs.get(index + 1));
								figs.set(index + 1, tmp);
							}
							break;
						case KeyEvent.VK_D:
							index = figs.indexOf(focus);

							if (index > 0) {
								Figure tmp = figs.get(index);
								figs.set(index, figs.get(index - 1));
								figs.set(index - 1, tmp);
							}
							break;
						}
					}
					else {
						switch (e.getKeyCode()) {
						case KeyEvent.VK_Q:
							figs.add(new Line(
								mouseX, mouseY,
								mouseX+100, mouseY+60)
							);
							break;
						case KeyEvent.VK_W:
							figs.add(new Triangle(
								mouseX, mouseY,
								mouseX - 50, mouseY + 50,
								mouseX + 50, mouseY + 50)
							);
							break;
						case KeyEvent.VK_E:
							figs.add(new Ellipse(
								mouseX, mouseY,
								100, 60)
							);
							break;
						case KeyEvent.VK_R:
							figs.add(new Rect(
								mouseX, mouseY,
								100, 60)
							);
							break;
						case KeyEvent.VK_T:
							figs.add(new Text(
								mouseX,
								mouseY,
								"Texto",
								30,
								"SansSerif"
							));
							break;


						//V20j:s/30/10/gc
						case KeyEvent.VK_RIGHT:
						case KeyEvent.VK_L:
							if (focus != null) {
								focus.drag(30, 0);
							}
							break;
						case KeyEvent.VK_LEFT:
						case KeyEvent.VK_H:
							if (focus != null) {
								focus.drag(-30, 0);
							}
							break;
						case KeyEvent.VK_UP:
						case KeyEvent.VK_K:
							if (focus != null) {
								focus.drag(0, -30);
							}
							break;
						case KeyEvent.VK_DOWN:
						case KeyEvent.VK_J:
							if (focus != null) {
								focus.drag(0, 30);
							}
							break;


						case KeyEvent.VK_D:
						case KeyEvent.VK_DELETE:
						case KeyEvent.VK_BACK_SPACE:
							if (focus != null) {
								figs.remove(focus);

								if (hover == focus) {
									hover = null;
								}
							}
							focus = null;
							break;
						case KeyEvent.VK_O:
							index = figs.indexOf(focus);
							changeFocus();
							break;
						case KeyEvent.VK_C:
							if (focus != null) {
								focus.changeColor(EveFrame.this);
							}
							break;
						}
					}
					repaint();
				}
			}
		);
	}
	void changeFocus() {
		if (figs.isEmpty()) {
			return;
		}

		if (focus == null ||
				(index = figs.indexOf(focus)) == 0) {
			focus = figs.get(figs.size() - 1);
		}
		else {
			index = (index - 1 + figs.size()) % figs.size();
			focus = figs.get(index);
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		int w = getWidth();
		int h = getHeight();

		Color focusColor = new Color(255, 0, 0);
		Color hoverColor = new Color(0, 0, 255);

		BasicStroke focusStroke = new BasicStroke(
			3.0f,
			BasicStroke.CAP_BUTT,
			BasicStroke.JOIN_MITER,
			10.0f,
			new float[] {5.0f, 5.0f},
			0.0f
		);
		BasicStroke hoverStroke = new BasicStroke(2.0f);

		for (Figure fig : figs) {
			fig.paint(g2d);
		}

		if (focus != null) {
			focus.paintFocus(g2d, focusColor, focusStroke);
			focus.paintHandle(g2d, new Color(0, 0, 255), new BasicStroke(2.0f));
		}
		if (hover != null && hover != focus) {
			hover.paintHover(g2d, hoverColor, hoverStroke);
		}
	}
}
