
package figuras;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.BasicStroke;
import java.awt.geom.Line2D;
import java.awt.Color;



public class Reta {
	private int x1, y1;
	private int x2, y2;

	private Color corContorno;
	private int tamanhoContorno = 1;


	public Reta(int x1, int y1, int x2, int y2, Color corContorno)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.corContorno = corContorno;
	}
	public Reta( // constructor overloading
			int x1, int y1, int x2, int y2, Color corContorno,
			int tamanhoContorno)
	{
		this(x1,y1,x2,y2,corContorno);
		this.tamanhoContorno = tamanhoContorno;
	}

	public void desenhar(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;

		var reta = new Line2D.Float(
				(float) x1, (float) y1,
				(float) x2, (float) y2
		);
		g2.setPaint(corContorno);
		g2.setStroke(new BasicStroke(tamanhoContorno));
		g2.draw(reta);
	}
}
