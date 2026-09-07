package figures;

import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;

public abstract class Figure {
	protected int x;
	protected int y;

	protected double angle = 0;

	protected Color borderColor;
	protected Color fillColor;

	protected int handle_gap = 10;
	protected BasicStroke stroke = new BasicStroke(2.0f);

	public Figure(int x, int y) {
		this.x = x;
		this.y = y;

		this.borderColor = Color.BLACK;
		this.fillColor = Color.WHITE;
	}

	public abstract boolean contains(int x, int y);

	public abstract void drag(int dx, int dy);

	public abstract void resize(int dx, int dy);

	public void rotate(double delta) {
		angle += delta;
	}

	public abstract void paint(Graphics g);

	public abstract void paintFocus(Graphics2D g2d, Color color, BasicStroke stroke);

	public abstract void paintHover(Graphics2D g2d, Color color, BasicStroke stroke);

	public abstract void paintHandle(Graphics2D g2d, Color focusColor, BasicStroke focusStroke);

	public abstract boolean resizeContains(int x, int y);

	public abstract void changeColor(JFrame frame);


	// setter de cor de contorno
	public void setBorderColor(Color color) {
		this.borderColor = color;
	}
	// setter de cor de preenchimento
	public void setFillColor(Color color) {
		this.fillColor = color;
	}

	public double getAngle() {
		return this.angle;
	}
}
