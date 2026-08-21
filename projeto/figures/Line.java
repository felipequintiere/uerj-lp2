package figures;

import java.awt.*;
import java.awt.geom.Line2D;

public class Line extends Figure {
	private int x2, y2;

	public Line(int x1, int y1, int x2, int y2) {
		super(x1, y1);

		this.x2 = x2;
		this.y2 = y2;
	}
	public Line(int x1, int y1, int x2, int y2, Color borderColor) {
		super(x1, y1);

		this.x2 = x2;
		this.y2 = y2;

		this.borderColor = borderColor;
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(borderColor);
		g2d.draw(new Line2D.Double(x, y, x2, y2));
	}

	public boolean contains(int x, int y) {
		Line2D line = new Line2D.Double(this.x, this.y, x2, y2);

		return line.ptSegDist(x, y) <= 5;
	}
}
