package vue;

import model.CaseModel;
import model.JardinModel;

import javax.swing.*;
import java.awt.*;

public class JardinVue extends JFrame {
    private final JardinModel jardinModel;

    public JardinVue(JardinModel model) {
        super();
        this.jardinModel = model;
        initialize();
    }

    public void initialize() {
        setSize(500, 400);
        setLayout(new GridLayout(10, 10));
        setTitle("HarvestMoon2");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        for (int i = 0; i < 100; i++) {
            CaseModel caseModel = this.jardinModel.getCase(i / this.jardinModel.getRows(), i % this.jardinModel.getCols());
            CaseVue caseJpanel = new CaseVue(caseModel);
            caseModel.addObserver(caseJpanel);
            caseModel.NotiAll();

            this.add(caseJpanel); // add à la grid
        }

        setVisible(true);
    }

//    @Override
//    public void update(Observable o, Object arg) {
//        boolean[][] plants = (boolean[][]) (arg);
//        System.out.println("receive plants");
//        for (int i = 0; i < 100; i++) {
//            this.cases.get(i).newPaint(plants[i / this.model.getRows()][i % this.model.getCols()]);
//            this.cases.get(i).setSelected(plants[i / this.model.getRows()][i % this.model.getCols()]);
//        }
//    }
}
