package figures;

import java.awt.*;

public abstract class Figure {
	protected int x, y;
	protected Color borderColor;
	protected Color fillColor;

	public Figure(int x, int y) {
		this.x = x;
		this.y = y;

		this.borderColor = Color.white;
		this.fillColor = Color.black;
	}

	public abstract void paint(Graphics g);

	public abstract boolean contains(int x, int y);

	public void changeBorder() {
		this.borderColor = new Color(255,100,100);
	}

	public void drag(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
