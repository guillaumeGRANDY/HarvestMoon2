import model.Case;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class CaseJpanel extends JPanel implements Observer {
    private final Case caseModel;
    private boolean selected;

    public CaseJpanel(Case caseModel) {
        super();
        selected = false;
        this.caseModel = caseModel;

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("clic");
                caseModel.changePlanted();
            }
        });
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void newPaint(boolean bool) {
        if (bool) {
            setBackground(Color.RED);
        } else {
            setBackground(Color.WHITE);
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        if ((boolean)(arg)) {
            setBackground(Color.RED);
        } else {
            setBackground(Color.WHITE);
        }
    }
}
