package org.polytech.vue;


import org.polytech.model.CaseModel;
import org.polytech.model.Ordonnanceur;
import org.polytech.model.legume.LegumeModel;
import org.polytech.model.legume.Tomate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class CaseVue extends JPanel implements Observer, MouseListener {
    private Image image ;
    private JLabel labelImage=new JLabel();
    private CaseModel caseModel;

    private JardinVue parent;

    public CaseVue() {
        ImageIcon imageIcon = new ImageIcon("./src/main/java/org/polytech/img/vide.png");
        image = imageIcon.getImage();
        this.add(labelImage,BorderLayout.CENTER);
        image = new ImageIcon("./src/main/java/org/polytech/img/vide.png").getImage();
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
        image= new ImageIcon("./src/main/java/org/polytech/img/"+stage+".png").getImage();
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
