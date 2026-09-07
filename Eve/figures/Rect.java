package figures;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Rect extends Figure {
	private int width;
	private int height;

	public Rect(int x, int y, int width, int height) {
		super(x,y);

		this.width = width;
		this.height = height;
	}

	public boolean contains(int x, int y) {
		Rectangle2D rectangle = new Rectangle2D.Double(
			this.x,
			this.y,
			width,
			height
		);

		return rectangle.contains(x,y);
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


		g2d.setColor(fillColor);
		g2d.fillRect(x, y, width, height);

		g2d.setColor(borderColor);
		g2d.setStroke(this.stroke); // stroke da classe Figure
		g2d.drawRect(x, y, width, height);


		g2d.setTransform(saveAT);
	}

	public void paintFocus(Graphics2D g2d, Color color, BasicStroke stroke) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, x + (width/2), y + (height/2));


		g2d.setColor(color);
		g2d.setStroke(stroke);
		g2d.drawRect(x, y, width, height);


		g2d.setTransform(saveAT);
	}
	public void paintHover(Graphics2D g2d, Color color, BasicStroke stroke) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, x + (width/2), y + (height/2));


		g2d.setColor(color);
		g2d.setStroke(stroke);
		g2d.drawRect(x, y, width, height);


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
