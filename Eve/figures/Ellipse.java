package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class Ellipse extends Figure {
	private int w, h;

	public Ellipse(int x, int y, int w, int h) {
		super(x,y);

		this.w = w;
		this.h = h;
	}
	public Ellipse(int x, int y, int w, int h,
			Color fillColor, Color borderColor) {
		super(x,y);
		this.w = w;
		this.h = h;

		this.fillColor = fillColor;
		this.borderColor = borderColor;
	}

	//public void print () {...}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(fillColor);
		g2d.fill(new Ellipse2D.Double(x, y, w, h));

		g2d.setColor(borderColor);
		g2d.draw(new Ellipse2D.Double(x, y, w, h));
	}

	public void drag (int dx, int dy) {
		super.x += dx;
		super.y += dy;
	}

	public boolean contains(int x, int y) {
		Ellipse2D ellipse =
			new Ellipse2D.Double(this.x, this.y, w, h);

		return ellipse.contains(x, y);
	}
}
