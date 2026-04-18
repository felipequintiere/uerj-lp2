/*
 * Considere as 2 figuras mais complexas da sua resposta à pergunta 3.1 do módulo 1.
 *
 * 1. implemente uma struct em C para cada uma delas e uma classe em java para a outra
 * 2. implemente uma função em C e um método em Java "print" para elas
 *
 * As informações contidas na struct e class precisam ser suficientes para representar a figura de forma completa. Não é necessário realmente desenhar as figuras na tela, mas somente com essas informações teria que ser possível desenhá-las de forma não ambígua
*/

public class Figura2DApp {
	static void main(String[] args)
	{
		Figura r1 = new Estrela(0,0,5,10,6);
		r1.print();
	}
}

abstract class Figura
{
	protected float x;
	protected float y;

	public Figura(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public abstract void print();
}

class Estrela extends Figura
{
	private int vertices;
	private float raioExterno;
	private float raioInterno;

	public Estrela(float x, float y, int vertices, float raioExterno, float raioInterno)
	{
		super(x,y);
		this.vertices = vertices;
		this.raioExterno = raioExterno;
		this.raioInterno = raioInterno;
	}

	public void print()
	{
		System.out.println("Estrela:");
		System.out.println("posição x: " + x);
		System.out.println("posição y: " + y);
		System.out.println("n° de vértices: " + vertices);
		System.out.println("raio externo: " + raioExterno);
		System.out.println("raio interno: " + raioInterno);
	}
}

class Elipse extends Figura
{
	private float eixoX;
	private float eixoY;

	public Elipse(float x, float y, float eixoX, float eixoY)
	{
		super(x,y);
		this.eixoX = eixoX;
		this.eixoY = eixoY;
	}

	public void print()
	{
		System.out.println("Elipse:");
		System.out.println("posição x: " + x);
		System.out.println("posição y: " + y);
		System.out.println("semi-eixo x: " + eixoX);
		System.out.println("semi-eixo y: " + eixoY);
	}
}


