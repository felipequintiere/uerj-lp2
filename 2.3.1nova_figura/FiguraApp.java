/*
1. Crie uma nova classe no pacote de figuras.
2. Use os modificadores de acesso apropriados.
3. Adicione novas figuras ao programa principal.
4. Adicione um "print screen" da execução (imagem .png)
*/

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
// the Swing classes are placed in the javax.swing package
//
// 'javax' indicates a Java extension package, not a core package;
// for historical reasons, Swing is considered an extension; however, 
// it is present in every Java implementation

import figuras.*;

/*
predefined constants for the following 13 standard colors:

  BLACK, BLUE, CYAN, DARK_GRAY, GRAY, GREEN, LIGHT_GRAY,
  MAGENTA, ORANGE, PINK, RED, WHITE, YELLOW
*/


public class FiguraApp
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() ->
				{
					var frame = new SimpleFrame();
					frame.setTitle("javaex");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					// to use their preferred size
					frame.pack();

					/*
					Toolkit kit = Toolkit.getDefaultToolkit();
					Dimension screenSize = kit.getScreenSize();
					int screenWidth = screenSize.width;
					int screenHeight = screenSize.height;

					// faz com que a JANELA tenha metade da altura e
					// metade da largura
					frame.setSize(screenWidth / 2, screenHeight / 2);
					// setSize ignora completamante o getPreferredSize()
					*/

					frame.setLocationRelativeTo(null);
					// constructing a frame does not automatically display it
					frame.setVisible(true);
				});
	}
}

// janela principal
class SimpleFrame extends JFrame
{
	public SimpleFrame()
	{
		// adiciona um componente
		add(new SimpleComponent());

		// ajusta o tamanho baseado no getPreferredSize() do componente
		pack();
	}
}

// componente desenhado na tela
class SimpleComponent extends JComponent
{
	//private static final int DEFAULT_WIDTH = 300;
	//private static final int DEFAULT_HEIGHT = 200;

	private static final int MESSAGE_X = 75;
	private static final int MESSAGE_Y = 100;

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawString("Teste", MESSAGE_X, MESSAGE_Y);
		Graphics2D g2 = (Graphics2D) g;

		// paint background manually
		g2.setPaint(new Color(0,0,0));
		g2.fillRect(0, 0, getWidth(), getHeight());


		// x, y, w, h, corFundo, corContorno, tamanhoContorno
		Retangulo retangulo = new Retangulo(
				240,135, 480,270,
				(new Color(0,128,128)),(Color.PINK),
				5
		);
		retangulo.desenhar(g2);

		// x, y, w, h, corFundo, corContorno, tamanhoContorno
		Elipse elipse = new Elipse(
				240,135, 480,270,
				Color.PINK,Color.CYAN,
				3
		);
		elipse.desenhar(g2);

		// x1, y1, x2, y2, corContorno, tamanhoContorno
		Reta reta = new Reta(
				240,135, 720,405,
				(new Color(231,104,249)),
				4
		);
		reta.desenhar(g2);
	}

	// isso não fixa o tamanho, apenas define um tamanho PREFERIDO
	public Dimension getPreferredSize()
	{
		// fazer com que o componente tenha metade da altura e metade
		// da largura
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;

		return new Dimension(screenWidth/2,screenHeight/2);
	}
}

