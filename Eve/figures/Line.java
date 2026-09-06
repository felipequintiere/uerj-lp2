package figures;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

public class Line extends Figure {
	private int x2, y2;

	public Line(int x, int y, int x2, int y2) {
		super(x, y);

		this.x2 = x2;
		this.y2 = y2;
	}

	public void drag (int dx, int dy) {
		x += dx;
		y += dy;
		x2 += dx;
		y2 += dy;
	}

	public boolean contains(int x, int y) {
		Line2D line = new Line2D.Double(this.x, this.y, x2, y2);

		// se a distância do mouse até a reta for menor ou igual a 5,
		// retorna 'true'
		return line.ptSegDist(x, y) <= 5;
	}

        public void resize(int dx, int dy) {
                x2 += dx;
                y2 += dy;
        }

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(
			angle,
			(x+x2)/2.0,
			(y+y2)/2.0
		);

		g2d.setColor(borderColor);
		g2d.draw(new Line2D.Double(x, y, x2, y2));

		g2d.setTransform(saveAT);
	}

	public void paintFocus(Graphics2D g2d, Color color, BasicStroke stroke) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(
			angle,
			(x+x2)/2.0,
			(y+y2)/2.0
		);

		g2d.setColor(color);
		g2d.setStroke(stroke);
		//g2d.drawRect(x, y, x2-x, y2-y); // mais complexo
		g2d.draw(new Line2D.Double(x, y, x2, y2));

		g2d.setTransform(saveAT);
	}
	public void paintHover(Graphics2D g2d, Color color, BasicStroke stroke) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(
			angle,
			(x+x2)/2.0,
			(y+y2)/2.0
		);

		g2d.setColor(color);
		g2d.setStroke(stroke);
		g2d.draw(new Line2D.Double(x, y, x2, y2));

		g2d.setTransform(saveAT);
	}

	public void paintHandle(Graphics2D g2d, Color color, BasicStroke stroke) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, (x + x2)/2, (y + y2)/2);

		g2d.setColor(color);
		g2d.setStroke(stroke);
		g2d.drawRect(
			x2-handle_gap,
			y2-handle_gap,
			2*handle_gap,
			2*handle_gap
		);

		g2d.setTransform(saveAT);

		// posição real do handle
		g2d.drawRect(
			x2-handle_gap,
			y2-handle_gap,
			2*handle_gap,
			2*handle_gap
		);
	}

	public boolean resizeContains(int x, int y) {
		Rectangle2D rectangle = new Rectangle2D.Double(
			x2 - handle_gap,
			y2 - handle_gap,
			2 * handle_gap,
			2 * handle_gap
		);

		return rectangle.contains(x,y);
	}
}
