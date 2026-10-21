package ivisible;

import java.awt.Graphics2D;

public interface IVisible {
	public boolean clicked(int x, int y);
	public void paint(Graphics2D g2d, boolean focused);
}
