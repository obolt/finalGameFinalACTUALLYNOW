import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class BallRoom extends JPanel {
    private int w, l;
    private ArrayList<Ball> ballList;
    private boolean toStart;
    private int time = 40;
    private boolean highScoreIs = false;
    // private boolean canAdvance = true;
    private int highest, wait;
    private int c;
    private boolean blah = true;
    private JLabel label, timeFor, highScore, space;



    public BallRoom(int width, int length) {
        w = width;
        l = length;
        setBackground(new Color(255, 182, 71));
        ballList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ballList.add(new Ball());
        }

        addKeyListener(new MyKeyListener());
        addMouseListener(new myMouse());
        isFocusable();
        setFocusable(true);

        addKeyListener(new MyKeyListener());
        addMouseListener(new myMouse());

        label = new JLabel("Press a ball to start!!");
        label.setFont(new Font("SANS_SERIF", Font.PLAIN, 30));
        add(label);

        timeFor = new JLabel((" - Time: " + time + "seconds"));
        timeFor.setFont(new Font("SANS_SERIF", Font.PLAIN, 30));
        add(timeFor);

        highScore = new JLabel("");
        highScore.setFont(new Font("SANS_SERIF", Font.PLAIN, 30));
        add(highScore);

        space = new JLabel("");
        space.setFont(new Font("SANS_SERIF", Font.PLAIN, 30));
        add(space);
    }

    public boolean toStart() {
        if (toStart && !isOver())
            return true;
        else
            return false;
    }

    public boolean isOver() {
        if (time == 0 || ballList.isEmpty())
            return true;
        return false;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < ballList.size(); i++) {
            ballList.get(i).draw(g);
            ballList.get(i).move(this, g);
        }

        try {
            Thread.sleep(100);
            if (toStart()) {
                if (c > highest) {
                    label.setText("Score: " + c + " - High score: " + highest);
                    label.setFont(new Font("SANS_SERIF", Font.PLAIN, 30));

                } else
                    label.setText("Score: " + c);
                wait++;
                if (wait == 11) {
                    time--;
                    wait = 0;
                }
            } else {
                label.setText("Press a ball to start!!");
                label.setFont(new Font("SANS_SERIF", Font.PLAIN, 30));
            }

        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        if (time <= 10) {
            if (time % 2 == 0)
                timeFor.setForeground(Color.red);
            else
                timeFor.setForeground(Color.black);
        }

        timeFor.setText(" - Time: " + time + " seconds ");

        if (isOver()) {
            while (blah) {
                ballList.clear();
                repaint();
                blah = false;
            }

            highScoreIs = true;

            label.setFont(new Font("SANS_SERIF", Font.PLAIN, 30));
            label.setText("Final Results:");

            // timeFor.setForeground(Color.black);
            timeFor.setFont(new Font("SANS_SERIF", Font.PLAIN, 30));
            timeFor.setText("Final Score: " + c);
            highScore.setText("- High Score: " + highest);

            // if (canAdvance)
            space.setText("Play Again? (press space)");

            if (c >= highest) {
                highest = c;
            }

        } else
            repaint();
    }

    public class MyKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_SPACE) {
                if (time == 0 || ballList.isEmpty()) {
                    toStart = false;
                    time = 40;
                    c = 0;
                    wait = 0;
                    blah = true;

                    highScore.setText("High Score: " + highest);
                    label.setFont(new Font("SANS_SERIF", Font.PLAIN, 30));
                    space.setText("");
                    timeFor.setForeground(Color.black);


                    for (int i = 0; i < 20; i++) {
                        ballList.add(new Ball());
                    }

                    repaint();
                }
            }
        }
    }

    public class myMouse extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            toStart = true;
            int x = e.getX();
            int y = e.getY();
            int clicPos = 0;

            for (int i = 0; i < ballList.size(); i++) {
                if (ballList.get(i).getSized() == e.getX()) {
                    ballList.remove(i);
                }

                clicPos = (int) (Math.sqrt(Math.abs(y - ballList.get(i).getY()) * Math.abs(y - ballList.get(i).getY()) + Math.abs(x - ballList.get(i).getX()) * Math.abs(x - ballList.get(i).getX())));

                if (clicPos < ballList.get(i).getSized()) {
                    c += ballList.get(i).getSized();
                    ballList.remove(i);
                    i--;
                }
            }
        }
    }
}
