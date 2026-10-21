package figures;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Triangle extends Figure {
	private int x2, y2;
	private int x3, y3;

	private int vertex = 0;
	private Polygon triangle = new Polygon();


	public Triangle(int x, int y, int x2, int y2, int x3, int y3) {
		super(x,y);

		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;

		triangle.addPoint(this.x, this.y);
		triangle.addPoint(x2, y2);
		triangle.addPoint(x3, y3);
	}


	@Override
	public void drag(int dx, int dy) {
		x += dx;
		y += dy;
		x2 += dx;
		y2 += dy;
		x3 += dx;
		y3 += dy;

		triangle.xpoints[0] = x;
		triangle.xpoints[1] = x2;
		triangle.xpoints[2] = x3;

		triangle.ypoints[0] = y;
		triangle.ypoints[1] = y2;
		triangle.ypoints[2] = y3;

		triangle.invalidate();
	}


	public boolean clicked(int x, int y) {
		return triangle.contains(x, y);
	}
	public boolean resizeClicked(int x, int y) {
		vertex = 0;

                Rectangle2D rectangle1 = new Rectangle2D.Double(
                        this.x - super.handle_gap,
                        this.y - super.handle_gap,
                        2 * super.handle_gap,
                        2 * super.handle_gap
		);
                Rectangle2D rectangle2 = new Rectangle2D.Double(
                        x2 - super.handle_gap,
                        y2 - super.handle_gap,
                        2 * super.handle_gap,
                        2 * super.handle_gap
		);
                Rectangle2D rectangle3 = new Rectangle2D.Double(
                        x3 - super.handle_gap,
                        y3 - super.handle_gap,
                        2 * super.handle_gap,
                        2 * super.handle_gap
		);
	
		if (rectangle1.contains(x,y)) {
			vertex = 1;
			return true;
		}
		else if (rectangle2.contains(x,y)) {
			vertex = 2;
			return true;
		}
		else if (rectangle3.contains(x,y)) {
			vertex = 3;
			return true;
		}
		else {
			return false;
		}
	}


	public void resize(int dx, int dy) {
		switch (vertex) {
		/*case 0: // ponteiro fora dos vértices
			y -= dy;
			x2 -= dx;
			y2 += dy;
			x3 += dx;
			y3 += dy;
			break;
		*/
		case 1:
			x += dx;
			y += dy;
			break;
		case 2:
			x2 += dx;
			y2 += dy;
			break;
		case 3:
			x3 += dx;
			y3 += dy;
			break;
		}

		triangle.xpoints[0] = x;
		triangle.xpoints[1] = x2;
		triangle.xpoints[2] = x3;

		triangle.ypoints[0] = y;
		triangle.ypoints[1] = y2;
		triangle.ypoints[2] = y3;

		triangle.invalidate();
	}


	public void paintHover(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, (x + x2+ x3)/3.0, (y + y2 + y3)/3.0);


		g2d.setColor(super.HOVER_COLOR);
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.drawPolygon(triangle);


		g2d.setTransform(saveAT);
	}
	public void paintFocus(Graphics2D g2d) {
		g2d.setColor(super.FOCUS_COLOR);
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.drawPolygon(triangle);
	}
	public void paintHandle(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, (x + x2 + x3)/3.0, (y + y2 + y3)/3.0);


		g2d.setColor(super.HOVER_COLOR);
		g2d.setStroke(new BasicStroke(2.0f));
                g2d.drawRect(
                        x - super.handle_gap,
                        y - super.handle_gap,
                        2 * super.handle_gap,
                        2 * super.handle_gap
                );
                g2d.drawRect(
                        x2 - super.handle_gap,
                        y2 - super.handle_gap,
                        2 * super.handle_gap,
                        2 * super.handle_gap
                );
                g2d.drawRect(
                        x3 - super.handle_gap,
                        y3 - super.handle_gap,
                        2 * super.handle_gap,
                        2 * super.handle_gap
                );


		g2d.setTransform(saveAT);
	}
	public void paint(Graphics2D g2d, boolean focused) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, (x + x2 + x3)/3.0, (y + y2 + y3)/3.0);


		g2d.setColor(super.fillColor);
		g2d.fillPolygon(triangle);

		g2d.setColor(super.borderColor);
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.drawPolygon(triangle);

		if (focused) {
			paintFocus(g2d);
		}


		g2d.setTransform(saveAT);
	}
}
