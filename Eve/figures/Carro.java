package figures;

import java.awt.*;

import figures.Rect;
import figures.Ellipse;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Carro extends Figure {
	private int width;
	private int height;

	Rect chassi;
	Ellipse roda1, roda2;

	public Carro(int x, int y, int width, int height) {
		super(x,y);
		this.width = width;
		this.height = height;

		this.chassi = new Rect(x, y, width, height/2);

		this.roda1 = new Ellipse(
			x,
			y + height/3,
			width/4,
			height/2
		);
		this.roda2 = new Ellipse(
			x + (3*width)/4,
			y + height/3,
			width/4,
			height/2
		);
	}

	public boolean contains(int x, int y) {
		// se pelo menos um dos objetos constituintes
		// do objeto carro retornar true quando
		// testado pela posição do ponteiro, então
		// esse objeto contém a posição atual do mouse
		return chassi.contains(x,y) ||
			roda1.contains(x,y) ||
			roda2.contains(x,y);
	}

	public void drag(int dx, int dy) {
		super.x += dx;
		super.y += dy;
	}

	public void resize(int dx, int dy) {
		width += dx;
		height += dy;

		if (width < 5) {
			width = 5;
		}

		if (height < 5) {
			height = 5;
		}

	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, x + (width/2), y + (height/2));


		chassi = new Rect(x, y, width, height/2);
		roda1 = new Ellipse(
			//x + width/4,
			x,
			y + height/3,
			width/4,
			height/2
		);
		roda2 = new Ellipse(
			x + (3*width)/4,
			y + height/3,
			width/4,
			height/2
			/*
			width/6,
			width/6
			*/
		);

		// definir a cor de contorno e cor de fundo de cada
		// um dos três objetos que formam o objeto Carro
		chassi.setBorderColor(this.borderColor);
		chassi.setFillColor(this.fillColor);
		chassi.paint(g);

		roda1.setBorderColor(this.borderColor);
		roda1.setFillColor(this.fillColor);
		roda1.paint(g);

		roda2.setBorderColor(this.borderColor);
		roda2.setFillColor(this.fillColor);
		roda2.paint(g);


		g2d.setTransform(saveAT);
	}

	public void paintFocus(Graphics2D g2d, Color color, BasicStroke stroke) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, x + (width/2), y + (height/2));


		g2d.setColor(color);
		g2d.setStroke(stroke);
		g2d.drawRect(x, y, width, height/2);
		g2d.draw(new Ellipse2D.Double(
			x,
			y + height/3,
			width/4,
			height/2
		));
		g2d.draw(new Ellipse2D.Double(
			x + (3*width)/4,
			y + height/3,
			width/4,
			height/2
		));


		g2d.setTransform(saveAT);
	}

	public void paintHover(Graphics2D g2d, Color color, BasicStroke stroke) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, x + (width/2), y + (height/2));


		g2d.setColor(color);
		g2d.setStroke(stroke);
		g2d.drawRect(x, y, width, height/2);
		g2d.draw(new Ellipse2D.Double(
			x,
			y + height/3,
			width/4,
			height/2
		));
		g2d.draw(new Ellipse2D.Double(
			x + (3*width)/4,
			y + height/3,
			width/4,
			height/2
		));


		g2d.setTransform(saveAT);
	}

	public void paintHandle(Graphics2D g2d, Color color, BasicStroke stroke) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, x + (width/2), y + (height/2));

		
		g2d.setColor(color);
		g2d.setStroke(stroke);
		g2d.drawRect(
			x + width - handle_gap,
			y + height - handle_gap,
			2 * handle_gap,
			2 * handle_gap
		);

		g2d.setTransform(saveAT);
	}

	public boolean resizeContains(int x, int y) {
		Rectangle2D rectangle = new Rectangle2D.Double(
			this.x + width - handle_gap,
			this.y + height - handle_gap,
			2 * handle_gap,
			2 * handle_gap
		);

		return rectangle.contains(x,y);
	}

	public void changeColor(JFrame frame) {
                String string;
                int hex;

                string = JOptionPane.showInputDialog(
                        frame, "Selecione a cor de contorno (R G B):"
                );
                hex = Integer.parseInt(string, 16);
                this.setBorderColor(new Color(hex));


                string = JOptionPane.showInputDialog(
                        frame, "Selecione a cor de fundo (R G B):"
                );
                hex = Integer.parseInt(string, 16);
                this.setFillColor(new Color(hex));
	}
}
