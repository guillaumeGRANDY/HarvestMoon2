package vue;

import model.CaseModel;
import model.Ordonnanceur;
import model.legume.LegumeModel;
import model.legume.Tomate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class CaseVue extends JPanel implements Observer, MouseListener {
    private Image image ;
    private JLabel labelImage=new JLabel();

    private CaseModel caseModel;

    public CaseVue() {
        ImageIcon imageIcon = new ImageIcon("./src/img/vide.png");
        image = imageIcon.getImage();
        this.add(labelImage,BorderLayout.CENTER);
        this.addMouseListener(this);
    }

    public CaseVue(CaseModel caseModel) {
        this();
        this.caseModel = caseModel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int dimension=Math.min(getHeight(),getHeight());
        ImageIcon icon = new ImageIcon(image.getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH));
        labelImage.setIcon(icon);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.setBackground(Color.LIGHT_GRAY);
        LegumeModel tomate = new Tomate();
        caseModel.plant(tomate);
        tomate.addObserver(CaseVue.this);
        Ordonnanceur.getInstance().addRunnable(tomate);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof LegumeModel legumeModel) {
            switch (legumeModel.getCurrentState().stateType()) {
                case GRAINE -> setBackground(Color.GREEN);
                case BOURGON -> setBackground(Color.YELLOW);
                case FLEURIE -> setBackground(Color.ORANGE);
                case MATURE -> setBackground(Color.RED);
            }
        }
    }
}
