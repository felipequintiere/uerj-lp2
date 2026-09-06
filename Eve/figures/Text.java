package figures;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Text extends Figure {
	private String text;
	private int size;
	private String face;

	public Text(int x, int y, String text, int size, String face) {
		super(x, y);

		this.text = text;
		this.size = size;
		this.face = face;
	}

	public boolean contains(int x, int y) {
		Font font = new Font(face, Font.PLAIN, size);

		FontMetrics metrics = new Canvas().getFontMetrics(font);

		int width = metrics.stringWidth(text);
		int height = metrics.getHeight();

		return x >= this.x &&
			x <= this.x + width &&
			y >= this.y - height &&
			y <= this.y;
	}

	public void drag(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	public void resize(int dx, int dy) {
		size += dy;

		if (size < 4)
			size = 4;
		}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		AffineTransform saveAT = g2d.getTransform();

		Font font = new Font(face, Font.PLAIN, size);
		g2d.setFont(font);

		g2d.rotate(angle, x, y);

		g2d.setColor(fillColor);
		g2d.drawString(text, x, y);

		g2d.setTransform(saveAT);
	}

	public void paintFocus(Graphics2D g2d, Color color, BasicStroke stroke) {
		AffineTransform saveAT = g2d.getTransform();

		Font font = new Font(face, Font.PLAIN, size);
		FontMetrics metrics = g2d.getFontMetrics(font);

		int width = metrics.stringWidth(text);
		int height = metrics.getHeight();

		g2d.rotate(angle, x, y);

		g2d.setColor(color);
		g2d.setStroke(stroke);
		g2d.drawRect(
			x,
			y - height,
			width,
			height
		);

		g2d.setTransform(saveAT);
	}

	public void paintHover(Graphics2D g2d, Color color, BasicStroke stroke) {
		AffineTransform saveAT = g2d.getTransform();

		Font font = new Font(face, Font.PLAIN, size);
		FontMetrics metrics = g2d.getFontMetrics(font);

		int width = metrics.stringWidth(text);
		int height = metrics.getHeight();

		g2d.rotate(angle, x, y);

		g2d.setColor(color);
		g2d.setStroke(stroke);
		g2d.drawRect(
			x,
			y - height,
			width,
			height
		);

		g2d.setTransform(saveAT);
	}

	public void paintHandle(Graphics2D g2d, Color color, BasicStroke stroke) {
		AffineTransform saveAT = g2d.getTransform();

		Font font = new Font(face, Font.PLAIN, size);
		FontMetrics metrics = g2d.getFontMetrics(font);

		int width = metrics.stringWidth(text);
		int height = metrics.getHeight();

		g2d.rotate(angle, x, y);

		g2d.setColor(color);
		g2d.setStroke(stroke);

		g2d.drawRect(
			x + width - handle_gap,
			y - height - handle_gap,
			2 * handle_gap,
			2 * handle_gap
		);

		g2d.setTransform(saveAT);
	}

	public boolean resizeContains(int x, int y) {
		Font font = new Font(face, Font.PLAIN, size);
		FontMetrics metrics = new Canvas().getFontMetrics(font);

		int width = metrics.stringWidth(text);
		int height = metrics.getHeight();

		return x >= this.x + width - handle_gap - 2 &&
			x <= this.x + width + 2 * handle_gap &&
			y >= this.y - height - handle_gap - 2 &&
			y <= this.y - height + 2 * handle_gap;
	}
}
