package figures;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Carro extends Figure {
	private int width;
	private int height;

	Rect chassi;
	Ellipse roda1, roda2;


	private void atualizarCarro() { // função auxiliar
		this.chassi = new Rect(
			x,
			y + (height / 3),
			width,
			height / 3,
			super.fillColor,
			super.borderColor,
			super.strokeWidth
		);

		this.roda1 = new Ellipse(
			x + (width / 6),
			y + (2 * height / 3),
			width / 4,
			height / 3,
			super.fillColor,
			super.borderColor,
			super.strokeWidth
		);

		this.roda2 = new Ellipse(
			x + (7 * width / 12),
			y + (2 * height / 3),
			width / 4,
			height / 3,
			super.fillColor,
			super.borderColor,
			super.strokeWidth
		);
	}


	public Carro(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;

		atualizarCarro();
	}


	@Override
	public void drag(int dx, int dy) {
		this.x += dx;
		this.y += dy;

		atualizarCarro();
	}

	public boolean clicked(int x, int y) {
		return chassi.clicked(x,y) ||
			roda1.clicked(x,y) ||
			roda2.clicked(x,y);
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

		atualizarCarro();
	}


	public void paintHover(Graphics2D g2d) {
		chassi.paintHover(g2d);
		roda1.paintHover(g2d);
		roda2.paintHover(g2d);
	}
	public void paintFocus(Graphics2D g2d) {
		chassi.paintFocus(g2d);
		roda1.paintFocus(g2d);
		roda2.paintFocus(g2d);
	}
	public void paintHandle(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, x + (width / 2), y + (height / 2));


		g2d.setColor(super.HOVER_COLOR);
		g2d.setStroke(new BasicStroke(super.HOVER_STROKE_WIDTH));		
		g2d.drawRect(
			x + width - handle_gap,
			y + height - handle_gap,
			2 * handle_gap,
			2 * handle_gap
		);


		g2d.setTransform(saveAT);
	}
	public void paint(Graphics2D g2d, boolean focused) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.rotate(super.angle, x + (width / 2), y + (height / 2));


		chassi.paint(g2d, false);
		roda1.paint(g2d, false);
		roda2.paint(g2d, false);

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
		super.setBorderColor(new Color(hex));


                string = JOptionPane.showInputDialog(
                        frame, "Selecione a cor de fundo (R G B):"
                );
                hex = Integer.parseInt(string, 16);
		super.setFillColor(new Color(hex));


		atualizarCarro();
	}
	
	@Override
	public void setStrokeWidth(float strokeWidth) {
		if (strokeWidth >= 1) {
			this.strokeWidth = strokeWidth;
			this.chassi.setStrokeWidth(strokeWidth);
			this.roda1.setStrokeWidth(strokeWidth);
			this.roda2.setStrokeWidth(strokeWidth);
		}
	}
}
