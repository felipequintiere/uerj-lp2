package figures;

import ivisible.IVisible;

import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.io.*;

public abstract class Figure implements IVisible, Serializable {
	protected int x;
	protected int y;

	protected double angle = 0;

	protected Color fillColor = Color.WHITE;
	protected Color borderColor = Color.BLACK;
	protected float strokeWidth = 2.0f;

	protected static final Color FOCUS_COLOR = Color.RED;
	protected static final float FOCUS_STROKE_WIDTH = 2.0f;

	protected static final Color HOVER_COLOR = Color.BLUE;
	protected static final float HOVER_STROKE_WIDTH = 2.0f;

	protected static final int HANDLE_GAP = 10;


	public Figure(int x, int y) {
		this.x = x;
		this.y = y;
	}


	public void drag(int dx, int dy) {
		this.x += dx;
		this.y += dy;
		// @Override em Line.java, Triangle.java, Carro.java
	}

	public void rotate(double delta) {
		this.angle += delta;
	}


	public abstract boolean clicked(int x, int y);
	public abstract boolean resizeClicked(int x, int y);

	public abstract void resize(int dx, int dy);

	public abstract void paintHover(Graphics2D g2d);
	public abstract void paintFocus(Graphics2D g2d);
	public abstract void paintHandle(Graphics2D g2d);
	public abstract void paint(Graphics2D g2d, boolean focused);


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
		// @Override em Line.java, Carro.java
	}



	// setters
	public void setBorderColor(Color color) {
		this.borderColor = color;
	}
	public void setFillColor(Color color) {
		this.fillColor = color;
	}
	public void setStrokeWidth(float strokeWidth) {
		if (strokeWidth >= 1) {
			this.strokeWidth = strokeWidth;
		}
	}

	// getters
	public double getAngle() {
		return this.angle;
	}
	public float getStrokeWidth() {
		return this.strokeWidth;
	}

}
