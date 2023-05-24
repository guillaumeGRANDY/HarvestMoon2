package vue;

import model.CaseModel;
import model.Ordonnanceur;
import model.legume.LegumeModel;
import model.legume.Tomate;
import model.legume.TypeLegume;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class CaseVue extends JPanel implements Observer, MouseListener {
    private Image image ;
    private JLabel labelImage=new JLabel();
    private CaseModel caseModel;

    private JardinVue parent;

    public CaseVue() {
        this.add(labelImage,BorderLayout.CENTER);
        image = new ImageIcon("./src/img/vide.png").getImage();
        ImageIcon icon = new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        labelImage.setIcon(icon);
        this.addMouseListener(this);
    }

    public CaseVue(CaseModel caseModel, JardinVue parent) {
        this();
        this.caseModel = caseModel;
        this.parent=parent;

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Mettez à jour l'image en fonction de la nouvelle taille de la fenêtre
                resizeImage();
            }
        });

    }

    public void changeImage(String stage)
    {
        image=new ImageIcon("./src/img/"+stage+".png").getImage();
        resizeImage();
    }

    public void resizeImage()
    {
        int dimension=Math.min(getWidth(),getHeight());
        ImageIcon icon = new ImageIcon( image.getScaledInstance(dimension, dimension, Image.SCALE_DEFAULT));
        labelImage.setIcon(icon);
        parent.repaint();
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        LegumeModel tomate = new Tomate();
        changeImage("stage1");
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
                case GRAINE -> changeImage("stage1");
                case BOURGON -> changeImage("stage2");
                case FLEURIE -> changeImage("stage3");
                case MATURE -> changeImage(legumeModel.getType().getName());
            }
            this.repaint();
        }
    }
}
