package figures;

import java.awt.*;

public class Rect extends Figure {
	private int width, height;

	public Rect (int x, int y, int width, int height) {
		this(x,y,width,height, Color.WHITE, Color.BLACK);
	}
	public Rect (int x, int y, int width, int height,
			Color fillColor, Color borderColor) {
		super(x,y);

		this.width = width;
		this.height = height;

		this.fillColor = fillColor;
		this.borderColor = borderColor;
	}

	public void paint (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(fillColor);
		g2d.fillRect(x, y, width, height);

		g2d.setColor(borderColor);
		g2d.drawRect(x, y, width, height);
	}

	public void drag (int dx, int dy) {
		super.x += dx;
		super.y += dy;
	}

	public boolean contains(int x, int y) {
		return x >= this.x && x <= this.x + width &&
		       y >= this.y && y <= this.y + height;
	}
}
