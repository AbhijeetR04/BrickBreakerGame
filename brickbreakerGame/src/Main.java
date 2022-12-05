import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        GamePlay gp = new GamePlay();
        jFrame.setBounds(10,10,700,600);
        jFrame.setResizable(false);
        jFrame.setTitle("Brick Breaker");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.add(gp);
    }

}
