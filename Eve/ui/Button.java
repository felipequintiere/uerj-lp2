package ui;

import java.awt.*;
import java.awt.Graphics2D;

import figures.*;
import ivisible.IVisible;

public class Button implements IVisible {
	public int index;
	private Figure figure;
	
	private int x = 10;	
	private int y = 20;
	private int width = 40;
	private int height = width;

	public Button(int index) {
		this.index = index;
		y += index * (height+1);

		switch (index) {
		case 0: 
			figure = new Line(
					  x + 5, y + 5,
					  x + width - 5, y + height - 5
					 );
			break;

		case 1:
			figure = new Triangle(
					      (x + width) / 2 + 5, y + 5,
					      x + 5, y + height - 5,
					      x + width - 5, y + height - 5
					     );
			break;

		case 2:
			figure = new Ellipse(x + 5, y + 5, 30, 30);
			break;

		case 3:
			figure = new Rect(x + 5, y + 5, 30, 30);
			break;

		case 4:
			figure = new Carro(x + 5, y + 5, 30, 30);
			break;
		}


		figure = figure;
	}

	public boolean clicked(int x, int y) {
		return x >= this.x && x <= this.x + width
			&& y >= this.y && y <= this.y + height;
	}


	public void paint(Graphics2D g2d, boolean focused) {
		g2d.setStroke(new BasicStroke(2.0f));

		if (focused)  {
			g2d.setColor(Color.LIGHT_GRAY);
		}
		else {
			g2d.setColor(Color.WHITE);
		}
		g2d.fillRect(x, y, width, height);


		g2d.setColor(Color.BLACK);
		g2d.drawRect(x, y, width, height);

		figure.paint(g2d, false);
	}
}
