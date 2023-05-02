import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new MainPanel().getTitlePanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
