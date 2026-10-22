package figures;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

public class Rect extends Figure {
	private int width;
	private int height;


	public Rect(int x, int y, int width, int height) {
		super(x, y);

		this.width = width;
		this.height = height;
	}
	public Rect(int x, int y, int width, int height,
		    Color fillcolor, Color borderColor, float strokeWidth) {
		super(x, y);

		this.width = width;
		this.height = height;

		super.fillColor = fillcolor;
		super.borderColor = borderColor;
		super.strokeWidth = strokeWidth;
	}


	public boolean clicked(int x, int y) {
		Rectangle2D rectangle = new Rectangle2D.Double(
			this.x, this.y,
			width, height
		);

		return rectangle.contains(x,y);
	}
	public boolean resizeClicked(int x, int y) {
		Rectangle2D rectangle = new Rectangle2D.Double(
			this.x + width - super.handle_gap,
			this.y + height - super.handle_gap,
			2 * super.handle_gap,
			2 * super.handle_gap
		);

		return rectangle.contains(x, y);
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


	public void paintHover(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, x + (width/2.0), y + (height/2.0));


		g2d.setColor(super.HOVER_COLOR);
		g2d.setStroke(new BasicStroke(super.HOVER_STROKE_WIDTH));
		g2d.drawRect(x, y, width, height);


		g2d.setTransform(saveAT);
	}
	public void paintFocus(Graphics2D g2d) {
		g2d.setColor(super.FOCUS_COLOR);
		g2d.setStroke(new BasicStroke(super.FOCUS_STROKE_WIDTH));
		g2d.drawRect(x, y, width, height);
	}
	public void paintHandle(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, x + (width/2), y + (height/2));


		g2d.setColor(super.HOVER_COLOR);
		g2d.setStroke(new BasicStroke(super.HOVER_STROKE_WIDTH));
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
		g2d.fillRect(x, y, width, height);

		g2d.setColor(super.borderColor);
		g2d.setStroke(new BasicStroke(super.strokeWidth));
		g2d.drawRect(x, y, width, height);

		if (focused) {
			paintFocus(g2d);
		}


		g2d.setTransform(saveAT);
	}
}
