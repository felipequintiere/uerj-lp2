/*
- Implemente os seguintes métodos:
1. int area ()
- deve retornar a área do retângulo
2. void drag (int dx, int dy)
- deve “arrastar” o objeto, ou seja somar o dx e dy a sua posição atual
- Altere o método `print` para também exibir a área.
- Inclua testes que garantam que os métodos estão corretos.
- Respeite as assinaturas de método acima.
- Não use `print` dentro dos métodos.
*/

public class RectApp {
	public static void main (String[] args) {

		Rect r1 = new Rect(1,1,10,10);
		r1.print();

		System.out.printf(
			"\nwidth: %d\nheight: %d\narea: %d\n" +
			"x: %d\ny: %d\n",
			r1.getWidth(),r1.getHeight(),r1.area(),r1.getX(),r1.getY());

		System.out.printf("\ndx: 10, dy: 10\n");
		r1.drag(10,10);
		r1.print();

	}
}

class Rect {
	private int x, y;
	private int w, h;

	public Rect (int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void drag(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	public int getWidth() {
		return this.w;
	}
	public int getHeight() {
		return this.h;
	}
	public int area() {
		return (this.w * this.h);	
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}

	public void print () {
		System.out.printf(
			"w: %d\nh: %d\n" +
			"x: %d\ny: %d\n",
			this.w,this.h,this.x,this.y);
	}
}
