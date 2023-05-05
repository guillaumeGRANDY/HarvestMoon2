import model.Ground;
import model.Ground2;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ground2 model = new Ground2(10, 10);
            View vue = new View(model);
            model.initGame();
        });
    }
}