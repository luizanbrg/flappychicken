import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 360;
        int boardHeight = 640;

        JFrame frame = new JFrame("Flappy Chicken!!");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FlappyChicken flappyChicken = new FlappyChicken();
        frame.add(flappyChicken);
        frame.pack();
        flappyChicken.requestFocus();
        frame.setVisible(true);
    }
}