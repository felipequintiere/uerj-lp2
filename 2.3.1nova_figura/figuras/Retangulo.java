
package figuras;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.geom.Rectangle2D;
import java.awt.BasicStroke;
import java.awt.Color;

public class Retangulo {
	private int x, y;
	private int w, h;

	private Color corFundo, corContorno;
	private int tamanhoContorno = 1;


	public Retangulo(
			int x, int y, int w, int h, 
			Color corFundo, Color corContorno)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.corFundo = corFundo;
		this.corContorno = corContorno;
	}
	public Retangulo(
			int x, int y, int w, int h, 
			Color corFundo, Color corContorno,
			int tamanhoContorno)
	{
		this(x,y,w,h,corFundo,corContorno);
		this.tamanhoContorno = tamanhoContorno;
	}


	public void desenhar(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;

		Rectangle2D.Float retangulo = new Rectangle2D.Float(
				(float) x, (float) y,
				(float) w, (float) h
		);
		g2.setPaint(corFundo);
		g2.fill(retangulo);


		g2.setStroke(new BasicStroke(tamanhoContorno));
		g2.setPaint(corContorno);
		g2.draw(retangulo);
	}
}
