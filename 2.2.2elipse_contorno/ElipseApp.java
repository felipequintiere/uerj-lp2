/*
2.2.1 - Desenhe o retângulo com cor de contorno e de fundo

1. Adicione à classe "Rect" as propriedades de cor de contorno e cor de fundo.
2. Altere o método "paint" para desenhar as cores de contorno e de fundo do retângulo.
- Use os métodos "drawRect" e "fillRect" do Java2D.
- Use o método "setColor" para alterar a cor vigente do Java2D.
Por exemplo, a chamada "g.setColor(new Color(0,255,0))" altera a cor de redesenho para verde.
3. Crie pelo menos 3 retângulos com propriedades diferentes e os exiba na tela.
4. Adicione um "print screen" da execução (imagem .png)
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ElipseApp {
	public static void main (String[] args) {
		PaintFrame frame = new PaintFrame();
		frame.setVisible(true);
	}
}

class Elipse {
	private int x, y;
	private int w, h;

	private Color fundo;
	private Color contorno;

	public Elipse (int x, int y, int w, int h, Color fundo, Color contorno) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.fundo = fundo;
		this.contorno = contorno;
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(this.fundo);
		g2d.fillOval(this.x, this.y,this.w, this.h);

		g2d.setColor(this.contorno);
		g2d.drawOval(this.x, this.y,this.w, this.h);
	}
}

class PaintFrame extends JFrame {
	Elipse r1, r2, r3, r4, r5, r6;

	public PaintFrame () {
		this.addWindowListener (
			new WindowAdapter() {
				public void windowClosing (WindowEvent e) {
					System.exit(0);
				}
			}
		);
		this.setTitle(" Elipse ");
		this.setSize(600, 600);
		this.getContentPane().setBackground(Color.BLACK);

		this.r1 = new Elipse(20,40, 60,90,Color.GREEN,Color.RED);
		this.r2 = new Elipse(30,180, 120,90,Color.CYAN,Color.GREEN);
		this.r3 = new Elipse(100,60, 100,70,Color.BLUE,Color.WHITE);
		this.r4 = new Elipse(200,150, 150,80,Color.GRAY,Color.CYAN);
		this.r5 = new Elipse(400,190, 140,120,Color.RED, new Color(33,139,235));
		this.r6 = new Elipse(100,330, 370,210,Color.WHITE, new Color(0,255,0));
	}

	public void paint (Graphics g) {
		super.paint(g);
		//int w = getWidth();
		//int h = getHeight();

		this.r1.paint(g);
		this.r2.paint(g);
		this.r3.paint(g);
		this.r4.paint(g);
		this.r5.paint(g);
		this.r6.paint(g);
	}
}
