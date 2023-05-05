import model.Case;
import model.Ground2;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private final Ground2 model;

    public View(Ground2 model) {
        super();
        this.model = model;
        initialize();
    }

    public void initialize() {
        setSize(500, 400);
        setLayout(new GridLayout(10, 10));
        setTitle("HarvestMoon2");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        for (int i = 0; i < 100; i++) {
            Case caseModel = this.model.getCase(i / this.model.getRows(), i % this.model.getCols());
            CaseJpanel caseJpanel = new CaseJpanel(caseModel);
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
