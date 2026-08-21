package figures;

import java.awt.*;

public class Rect extends Figure {
	private int w, h;

	public Rect (int x, int y, int w, int h) {
		super(x,y);

		this.w = w;
		this.h = h;
	}
	public Rect (int x, int y, int w, int h,
			Color fillColor, Color borderColor) {
		super(x,y);
		this.w = w;
		this.h = h;

		this.fillColor = fillColor;
		this.borderColor = borderColor;
	}

	//public void print () {...}

	public void paint (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(fillColor);
		g2d.fillRect(x, y, w, h);

		g2d.setColor(borderColor);
		g2d.drawRect(x, y, w, h);
	}

	public void drag (int dx, int dy) {
		super.x += dx;
		super.y += dy;
	}

	public boolean contains(int x, int y) {
		return x >= this.x && x <= this.x + w &&
		       y >= this.y && y <= this.y + h;
	}
}
