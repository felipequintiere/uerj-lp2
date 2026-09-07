package figures;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Triangle extends Figure {
	private int x2, y2;
	private int x3, y3;

	private Polygon triangle = new Polygon();

	private int vertex = 0;

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

	public boolean contains(int x, int y) {
		return triangle.contains(x, y);
	}

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

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, (x + x2 + x3)/3.0, (y + y2 + y3)/3.0);

		g2d.setColor(fillColor);
		g2d.fillPolygon(triangle);

		g2d.setColor(borderColor);
		g2d.setStroke(this.stroke); // stroke da classe Figure
		g2d.drawPolygon(triangle);

		g2d.setTransform(saveAT);
	}

	public void paintFocus(Graphics2D g2d, Color color, BasicStroke stroke) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, (x + x2+ x3)/3.0, (y + y2 + y3)/3.0);

		g2d.setColor(color);
		g2d.setStroke(stroke);
		g2d.drawPolygon(triangle);

		g2d.setTransform(saveAT);
	}
	public void paintHover(Graphics2D g2d, Color color, BasicStroke stroke) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, (x + x2+ x3)/3.0, (y + y2 + y3)/3.0);

		g2d.setColor(color);
		g2d.setStroke(stroke);
		g2d.drawPolygon(triangle);

		g2d.setTransform(saveAT);
	}

	public void paintHandle(Graphics2D g2d, Color color, BasicStroke stroke) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, (x + x2 + x3)/3.0, (y + y2 + y3)/3.0);


                g2d.setColor(color);
                g2d.setStroke(stroke);
                g2d.drawRect(
                        x - handle_gap,
                        y - handle_gap,
                        2 * handle_gap,
                        2 * handle_gap
                );
                g2d.setColor(color);
                g2d.setStroke(stroke);
                g2d.drawRect(
                        x2 - handle_gap,
                        y2 - handle_gap,
                        2 * handle_gap,
                        2 * handle_gap
                );
                g2d.setColor(color);
                g2d.setStroke(stroke);
                g2d.drawRect(
                        x3 - handle_gap,
                        y3 - handle_gap,
                        2 * handle_gap,
                        2 * handle_gap
                );


		g2d.setTransform(saveAT);
	}

	public boolean resizeContains(int x, int y) {
		vertex = 0;

                Rectangle2D rectangle1 = new Rectangle2D.Double(
                        this.x - handle_gap,
                        this.y - handle_gap,
                        2 * handle_gap,
                        2 * handle_gap
		);
                Rectangle2D rectangle2 = new Rectangle2D.Double(
                        x2 - handle_gap,
                        y2 - handle_gap,
                        2 * handle_gap,
                        2 * handle_gap
		);
                Rectangle2D rectangle3 = new Rectangle2D.Double(
                        x3 - handle_gap,
                        y3 - handle_gap,
                        2 * handle_gap,
                        2 * handle_gap
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
