import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class CaseJpanel extends JPanel implements Observer {
    private boolean selected;

    public CaseJpanel() {
        super();
        selected = false;

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

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
