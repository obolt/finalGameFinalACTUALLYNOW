import java.awt.*;
import javax.swing.*;

public class Ball extends JPanel {
    private int xPos, yPos, xVel, yVel, size, r, gr, b;
    private Color colord;

    public Ball() {
        size = (int) (Math.random() * 100 + 20);
        xVel = (int) (Math.random() * 6 + 1);
        yVel = (int) (Math.random() * 6 + 1);
        xPos = (int) (Math.random() * (400 - size));
        yPos = (int) (Math.random() * (400 - size));
        r = (int) (Math.random() * 255);
        gr = (int) (Math.random() * 255);
        b = (int) (Math.random() * 255);
        colord = new Color(r, gr, b);
    }

    public void draw(Graphics g) {
        g.setColor(colord);
        g.fillOval(xPos, yPos, size, size);
    }

    public void move(JPanel panel, Graphics g) {
        xPos += xVel;
        yPos += yVel;

        if ((xPos >= (panel.getWidth() - (size)) || (xPos <= 0)) || (yPos >= (panel.getHeight() - (size)) || (yPos <= 0))) {
            xVel *= -1;
            yVel *= -1;

            xPos += 3 * xVel;
            yPos += 3 * yVel;
        }
        draw(g);
    }

    public int getSized() {
        return size;
    }

    public int getY() {
        return yPos;
    }

    public int getX() {
        return xPos;
    }
}
