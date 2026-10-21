package figures;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Line extends Figure {
	private int x2, y2;


	public Line(int x, int y, int x2, int y2) {
		super(x, y);

		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public void drag (int dx, int dy) {
		super.x += dx;
		super.y += dy;
		x2 += dx;
		y2 += dy;
	}


	public boolean clicked(int x, int y) {
		Line2D line = new Line2D.Double(super.x, super.y, x2, y2);

		// se a distância do mouse até a reta for menor ou igual a 5,
		// retorna 'true'
		return line.ptSegDist(x, y) <= 5;
	}
	public boolean resizeClicked(int x, int y) {
		Rectangle2D rectangle = new Rectangle2D.Double(
			x2 - super.handle_gap,
			y2 - super.handle_gap,
			2 * super.handle_gap,
			2 * super.handle_gap
		);

		return rectangle.contains(x, y);
	}

        public void resize(int dx, int dy) {
                x2 += dx;
                y2 += dy;
        }


	public void paintHover(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(
			super.angle,
			(x + x2)/2.0,
			(y + y2)/2.0
		);


		g2d.setColor(super.HOVER_COLOR);
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.draw(new Line2D.Double(x, y, x2, y2));


		g2d.setTransform(saveAT);
	}
	public void paintFocus(Graphics2D g2d) {
		g2d.setColor(super.FOCUS_COLOR);
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.draw(new Line2D.Double(x, y, x2, y2));
		//g2d.drawRect(x, y, x2 - x, y2 - y); // mais complexo
	}
	public void paintHandle(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, (x + x2)/2, (y + y2)/2);

		g2d.setColor(super.HOVER_COLOR);
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.drawRect(
			x2 - super.handle_gap,
			y2 - super.handle_gap,
			2 * super.handle_gap,
			2 * super.handle_gap
		);


		g2d.setTransform(saveAT);
	}
	public void paint(Graphics2D g2d, boolean focused) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(
			angle,
			(x + x2)/2.0,
			(y + y2)/2.0
		);


		g2d.setColor(super.borderColor);
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.draw(new Line2D.Double(x, y, x2, y2));

		if (focused) {
			paintFocus(g2d);
		}


		g2d.setTransform(saveAT);
	}

	@Override
        public void changeColor(JFrame frame) {
                String string;
                int hex;

                string = JOptionPane.showInputDialog(
                        frame, "Selecione a cor de contorno (R G B):"
                );
                hex = Integer.parseInt(string, 16);
                this.setBorderColor(new Color(hex));
        }
}
