import model.Ground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {
    private final Ground ground;
    private final JPanel[][] cases = new JPanel[10][10];

    public View(Ground model) {
        super();
        this.ground = model;
        initialize();
    }

    public void initialize() {
        setSize(500, 400);
        setLayout(new GridLayout(10, 10));
        setTitle("HarvestMoon2");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                final int k = i;
                final int l = j;
                cases[i][j] = new JPanel();
                cases[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
                cases[i][j].setBackground(this.ground.getPlants()[i][j] ? Color.RED : Color.WHITE);
                cases[i][j].addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("clic");
                        ground.modifiePixel(k, l);
                    }
                });
                this.add(cases[i][j]); // add à la grid
            }
        }

        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        boolean[][] plants = (boolean[][]) (arg);
        System.out.println("receive plants");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cases[i][j].setBackground(this.ground.getPlants()[i][j] ? Color.RED : Color.WHITE);
            }
        }
    }
}
