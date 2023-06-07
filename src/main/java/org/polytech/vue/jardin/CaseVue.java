package org.polytech.vue.jardin;


import org.polytech.model.CaseModel;
import org.polytech.model.Ordonnanceur;
import org.polytech.model.exception.CannotHarvestException;
import org.polytech.model.legume.LegumeFabrique;
import org.polytech.model.legume.LegumeModel;
import org.polytech.model.legume.type.TypeLegume;
import org.polytech.utils.ExtensionImage;
import org.polytech.utils.Utils;
import org.polytech.vue.bottom.LegumeMenuItem;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

public class CaseVue extends JPanel implements Observer, MouseListener {
    private Image image ;
    private JLabel labelImage=new JLabel();
    private JLabel labelParticule=
    private CaseModel caseModel;

    private JardinVue parent;

    public CaseVue() {
        ImageIcon imageIcon = Utils.getImageIconFromResources("vide", ExtensionImage.PNG);
        image = imageIcon.getImage();
        this.add(labelImage,BorderLayout.CENTER);
        setCaseNotPlanted();
        ImageIcon icon = new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        labelImage.setIcon(icon);

        this.addMouseListener(this);
    }

    private void setCaseNotPlanted() {
        image = Utils.getImageIconFromResources("vide", ExtensionImage.PNG).getImage();
        this.labelImage.setIcon(new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
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
        Ordonnanceur.getInstance().addRunnable(caseModel);
    }

    public void changeImage(String stage)
    {
        image = Utils.getImageIconFromResources(stage, ExtensionImage.PNG).getImage();
        resizeImage();
    }

    public void resizeImage()
    {
        int dimension= Math.min(getWidth(),getHeight());
        ImageIcon icon = new ImageIcon( image.getScaledInstance(dimension, dimension, Image.SCALE_DEFAULT));
        labelImage.setIcon(icon);
        parent.repaint();
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("plant.wav")));
            Clip audioClip = AudioSystem.getClip();
            audioClip.open(audioInputStream);
            audioClip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if(!this.caseModel.isPlanted()) {
            this.buyAndPlant();
        } else {
            try {
                LegumeModel legumeToHarvest = this.caseModel.tryHarvest();
                this.parent.getJoueurModel().addToInventory(legumeToHarvest);
                this.setCaseNotPlanted();
                this.resizeImage();
            } catch (CannotHarvestException ex) {
                this.caseModel.getLegumeModel().croissance(10);
            }
        }
    }

    private void buyAndPlant() {
        Optional<LegumeMenuItem> optionalBottomMenuItem = Optional.ofNullable(parent.getMenuDown().getSelectedBottomItem());
        if(optionalBottomMenuItem.isPresent()) {
            TypeLegume typeLegume = optionalBottomMenuItem.get().getTypeLegume();
            LegumeModel legumeSelected = LegumeFabrique.createLegume(typeLegume);
            try {
                parent.getJoueurModel().buy(legumeSelected);
                this.plant(legumeSelected);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Vous n'avez pas assez d'argent");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez séléctionner un légume");
        }
    }

    private void plant(LegumeModel legume) {
        legume.plant();
        caseModel.plant(legume);
        changeImage("stage1");
        legume.addObserver(CaseVue.this);
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
                case MATURE -> changeImage("stage4" + legumeModel.getType().getImageName());
                case POURRIE -> System.out.println("pouuuuurie");
            }
            this.repaint();
        }
    }

    public void addParticles()
    {
        Timer timer = new Timer(1000, e -> {
        });
    }
}
