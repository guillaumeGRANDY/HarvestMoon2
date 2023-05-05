import model.Ground;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ground model = new Ground(10, 10);
            View vue = new View(model);
            model.addObserver(vue);
            model.initGame();
        });
    }
}