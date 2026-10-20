package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Ellipse extends Figure {
	private int width;
	private int height;


	public Ellipse(int x, int y, int width, int height) {
		super(x,y);

		this.width = width;
		this.height = height;
	}


	public boolean clicked(int x, int y) {
		return new Ellipse2D.Double(
			this.x, this.y,
			width, height
		).contains(x, y);
	}
	public boolean resizeClicked(int x, int y) {
		Rectangle2D rectangle = new Rectangle2D.Double(
			this.x + width - super.handle_gap,
			this.y + height - super.handle_gap,
			2 * super.handle_gap,
			2 * super.handle_gap
		);

		return rectangle.contains(x,y);
	}

        public void resize(int dx, int dy) {
                this.width += dx;
                this.height += dy;

                if (width < 5) {
                        width = 5;
		}

                if (height < 5) {
                        height = 5;
		}
        }


	public void paintHover(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, x + (width/2), y + (height/2));


		g2d.setColor(super.HOVER_COLOR);
		g2d.setStroke(super.HOVER_STROKE);
		g2d.drawRect(x, y, width, height);
		//g2d.draw(new Ellipse2D.Double(x, y, width, height));

		g2d.setTransform(saveAT);
	}
	public void paintFocus(Graphics2D g2d) {
		g2d.setColor(super.FOCUS_COLOR);
		g2d.setStroke(super.FOCUS_STROKE);
		g2d.drawOval(x, y, width, height);
	}

	public void paintHandle(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, x + (width/2), y + (height/2));


		g2d.setColor(super.HOVER_COLOR);
		g2d.setStroke(new BasicStroke(2.0f));
		//g2d.setStroke(super.HOVER_STROKE);
		g2d.drawRect(
			x + width - super.handle_gap,
			y + height - super.handle_gap,
			2 * super.handle_gap,
			2 * super.handle_gap
		);


		g2d.setTransform(saveAT);
	}
	public void paint(Graphics2D g2d, boolean focused) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, x + (width/2), y + (height/2));


		g2d.setColor(super.fillColor);
		g2d.fillOval(x, y, width, height);

		g2d.setColor(super.borderColor);
		g2d.setStroke(super.stroke);
		g2d.drawOval(x, y, width, height);

		if (focused) {
			paintFocus(g2d);
		}


		g2d.setTransform(saveAT);
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
