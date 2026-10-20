package ivisible;

import java.awt.*;
//import java.awt.Graphics;
//import java.awt.Graphics2D;

public interface IVisible {
	public boolean clicked(int x, int y);
	public void paint(Graphics2D g2d, boolean focused);
	// o parâmetro booleano em paint() serve para determinar
	// se o objeto deve ser desenhado com ou sem foco
}
