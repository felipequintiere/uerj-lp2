import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;

import java.awt.geom.AffineTransform;

import figures.*;
import ui.Button;

import java.io.*;

public class Eve {
	public static void main(String[] args) {
		EveFrame frame = new EveFrame();
	}
}

class EveFrame extends JFrame {
	private ArrayList<Figure> figs = new ArrayList<Figure>();
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private Button focusButton = null;

	// focus/hover sobre objeto
	private Figure focus = null;
	private Figure hover = null;

	private boolean resizing = false;

	private int mouseX;
	private int mouseY;

	private int index; // index da lista heterogênea

	@SuppressWarnings("unchecked")
	public EveFrame() {


		try {
			FileInputStream f = new FileInputStream("proj.bin");
			ObjectInputStream o = new ObjectInputStream(f);
			this.figs = (ArrayList<Figure>) o.readObject();
			o.close();

			System.out.printf("Arquivo carregado\n");
			this.repaint();
		}
		catch (Exception x) {
			System.out.printf("Nenhum arquivo para ler\n");
		}


		this.setTitle("Eve - Editor Vetorial");
		this.setSize(700, 700);
		this.setVisible(true);

		// buttons.add(new Button(0)); 
		buttons.add(new Button(0)); // line
		buttons.add(new Button(1)); // triangle
		buttons.add(new Button(2)); // ellipse
		buttons.add(new Button(3)); // rect
		buttons.add(new Button(4)); // carro
		//buttons.add(new Button(5)); // text

		// fechar a janela
		this.addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e) {


					try {
						FileOutputStream f = new FileOutputStream("proj.bin");
						ObjectOutputStream o = new ObjectOutputStream(f);
						o.writeObject(figs);
						o.flush();
						o.close();

						System.out.printf("Arquivo salvo\n");
					}
					catch (Exception x) {}


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

					int dx = e.getX() - mouseX;
					int dy = e.getY() - mouseY;

					mouseX = e.getX();
					mouseY = e.getY();

					if (resizing) {
						focus.resize(dx, dy);
					} else {
						focus.drag(dx, dy);
					}

					repaint();
				}

				public void mouseMoved(MouseEvent e) {
					mouseX = e.getX();
					mouseY = e.getY();

					Figure oldHover = hover;
					hover = null;

					for (Figure fig : figs) {
						if (fig.clicked(e.getX(), e.getY()) == true) {
							hover = fig;
						}
					}

					// funcionalidade de hover
					if (oldHover != hover) {
						repaint();
					}
				}
			}
		);
		this.addMouseListener(
			new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON1 && focusButton != null) {
						switch (focusButton.index) {
						case 0: 
							figs.add(new Line(
								mouseX, mouseY,
								mouseX + 100, mouseY + 60)
							);
							break;
						case 1:
							figs.add(new Triangle(
								mouseX, mouseY,
								mouseX - 50, mouseY + 50,
								mouseX + 50, mouseY + 50)
							);
							break;
						case 2:
							figs.add(new Ellipse(
								mouseX, mouseY,
								100, 60)
							);
							break;
						case 3:
							figs.add(new Rect(
								mouseX, mouseY,
								100, 60)
							);
							break;
						case 4:
							figs.add(new Carro(
								mouseX, mouseY,
								100, 60)
							);
							break;
						}

						focusButton = null;
						repaint();
					}
					else if (e.getButton() == MouseEvent.BUTTON1) {
						for (Button button : buttons) {
							if (button.clicked(e.getX(), e.getY())) {
								focusButton = button;
							}
						}

						if (focus != null &&
								focus.resizeClicked(e.getX(), e.getY())) {
							resizing = true;
						}
						else if (hover == null) {
							focus = null;
						}
						else if  (hover.clicked(e.getX(), e.getY())) {
							focus = hover;
						}
					}
					else if (e.getButton() == MouseEvent.BUTTON3) {
						if (hover == null) {
							focus = null;
						}
						else if (hover.clicked(e.getX(), e.getY())) {
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
								mouseX + 100, mouseY + 60)
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
							/*
						case KeyEvent.VK_T:
							figs.add(new Text(
								mouseX,
								mouseY,
								"Texto",
								30,
								"SansSerif"
							));
							break;
							*/

						// questao 1 [ item b ]
						case KeyEvent.VK_1:
							figs.add(new Carro(
								mouseX, mouseY,
								100, 60)
							);
							break;

						// questao 2
						case KeyEvent.VK_2:
							// índice do elemento em foco 
							int i = figs.indexOf(focus);

							// se não tiver elemento na lista heterogênea
							if (figs.isEmpty()) {
								return;
							} else {
								// se nenhuma figura estiver selecionada, o foco é
								// passado para o primeiro elemento da lista
								if (focus == null) {
									focus = figs.get(0);
								}
								// passa o foco para o elemento de índice sucessor;
								// a operação de módulo é utilizada para evitar que
								// ultrapasse o número de elementos da lista
								else {
									focus = figs.get((i + 1) % figs.size());
								}
							}

							break;


						// alterar a espessura da borda da figura
						case KeyEvent.VK_9:
							if (focus != null) {
								float aux = focus.getStrokeWidth();
								focus.setStrokeWidth(aux - 1.0f);
							}
							break;
						case KeyEvent.VK_0:
							if (focus != null) {
								float aux = focus.getStrokeWidth();
								focus.setStrokeWidth(aux + 1.0f);
							}
							break;


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
								Figure removedFigure = focus;

								if (figs.size() >= 2) {
									changeFocus();
								}
								else {
									focus = null;
								}

								figs.remove(removedFigure);

								if (hover == removedFigure) {
									hover = null;
								}
							}
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
	
		// desenha todas as figuras normalmente
		for (Figure fig : figs) {
			fig.paint(g2d, false);
		}

		// desenha o foco e o handle por cima de todas as figuras
		if (focus != null) {
			//focus.paint(g2d, true);
			focus.paintFocus(g2d); // problema com a rotação
			focus.paintHandle(g2d);
		}

		// funcionalidade de hover
		//if (hover != null) {
		if (hover != null && hover != focus) {
			hover.paintHover(g2d);
		}
		
		for (Button button : buttons) {
			button.paint(g2d, button == focusButton);
		}
	}
}
