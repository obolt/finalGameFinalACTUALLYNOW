import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        BallRoom room = new BallRoom(800, 800);
        frame.add(room);
        frame.setVisible(true);
    }
}