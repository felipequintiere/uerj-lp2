/*
Faça algumas modificações no programa:
a. altere as cores de fundo e dos gráficos
b. adicione alguma outra primitiva gráfica (retângulo, elipse, imagem, etc)
c. alguma outra modificação usando a sua criatividade
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hello2DApp {
    public static void main (String[] args) {
        Hello2DFrame frame = new Hello2DFrame();
    }
}

class Hello2DFrame extends JFrame {
    public Hello2DFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("meu título!");
        this.setSize(400, 400);
        this.setVisible(true);
    }

    public void paint (Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();

        // cor de fundo
        g2d.setPaint(Color.black);
        g2d.fillRect(0,0, w,h);

        g2d.setPaint(Color.yellow);
        g2d.drawLine(w/4,h/2, w/2,30);
        g2d.drawLine(w/2,30,3*(w/4),h/2);
        g2d.drawLine(w/4,h/2, w/2,h);
        g2d.drawLine(w/2,h,3*(w/4),h/2);

        g2d.setPaint(Color.blue);
        g2d.drawOval(w/8,h/8,w/4,h/4);
        g2d.drawOval(5*w/8,h/8,w/4,h/4);
        g2d.drawOval(w/8,5*h/8,w/4,h/4);
        g2d.drawOval(5*w/8,5*h/8,w/4,h/4);



        //g2d.drawLine(w/4,h/2, w/2,0);
        //g2d.drawLine(w/2,0,3*(w/4),h/2);
        //g2d.drawLine(w/4,h/2, w/2,h);
        //g2d.drawLine(w/2,h,3*(w/4),h/2);
        g2d.setPaint(Color.green);
        int pathxs[] = {0, w/2, w, w/2};
        int pathys[] = {h/2, 20, h/2, h};
        g2d.drawPolygon(pathxs, pathys, pathxs.length);
    }
}
