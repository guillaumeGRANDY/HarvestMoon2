package org.polytech.vue.jardin;

import org.polytech.model.JardinModel;
import org.polytech.model.JoueurModel;
import org.polytech.model.Ordonnanceur;
import org.polytech.model.PrixMarche;
import org.polytech.vue.Background;
import org.polytech.vue.bottom.BottomMenu;
import org.polytech.vue.inventory.InventoryMenu;
import org.polytech.vue.top.price.PriceMenu;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Objects;

public class JardinVue extends JFrame {
    private final JardinModel jardinModel;
    private final JoueurModel joueurModel;

    private Background background;

    private PriceMenu topMenu;

    public JoueurModel getJoueurModel() {
        return joueurModel;
    }

    private BottomMenu menuDown;

    public JardinVue(JardinModel model) {
        super();
        this.jardinModel = model;
        this.joueurModel = new JoueurModel(1000);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                revalidate();
            }
        });

        initialize();
    }

    public BottomMenu getMenuDown() {
        return menuDown;
    }

    public void initialize() {
        //fenetre
        this.setSize(1500, 1000);
        this.setTitle("HarvestMoon2");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("minecraft.wav")));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        background = new Background();
        GridBagLayout windowLayout = new GridBagLayout();
        background.setLayout(windowLayout);
        this.add(background);

        // Création des contraintes GridBagConstraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        //créer le bandeau supérieur
        topMenu = new PriceMenu();
        constraints.weightx = 1.0;
        constraints.weighty = 0.1; // To allocate space in the vertical direction
        constraints.fill = GridBagConstraints.HORIZONTAL; // To make it fill the horizontal space;
        constraints.gridx = 0; // To make sure it starts at the left
        constraints.gridy = 0; // To put it at the top
        constraints.gridwidth = 2; // To make it extend across all columns
        background.add(topMenu, constraints);

        //créer le jardin
        JPanel zoneJardin = new JPanel();
        zoneJardin.setBackground(new java.awt.Color(0, 0, 0, 0));
        GridLayout jardinLayout = new GridLayout(5, 8, 10, 10);
        zoneJardin.setLayout(jardinLayout);

        for (int i = 0; i < jardinModel.getRows(); i++) {
            for (int j = 0; j < jardinModel.getCols(); j++) {
                CaseVue uneCase = new CaseVue(jardinModel.getCase(i, j),this);
                uneCase.setBackground(new java.awt.Color(0, 0, 0, 0));
                zoneJardin.add(uneCase); // add à la grid
            }
        }

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.weightx = 2.0;
        constraints.weighty = 1.0; // To allocate more space to this component
        constraints.fill = GridBagConstraints.BOTH; // To make it fill the available space
        background.add(zoneJardin, constraints);

        //créer le menu lateral
        InventoryMenu menuLateral = new InventoryMenu(this.joueurModel);
        menuLateral.setBackground(new java.awt.Color(0, 0, 0, 0.5F));

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.weightx = 0.1;
        constraints.weighty = 0.1; // To allocate more space to this component
        background.add(menuLateral, constraints);

        //créer le menu inférieur
        menuDown = new BottomMenu();

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 0.1; // Réduire le poids pour allouer moins d'espace
        constraints.fill = GridBagConstraints.CENTER;
        background.add(menuDown, constraints);

        addAllObservers();
        initArgentLabel();

        this.setVisible(true);
        Ordonnanceur.getInstance().start();
    }

    public void addAllObservers()
    {
        this.jardinModel.getMeteo().addObserver(menuDown.getBarSoleil());
        this.jardinModel.getMeteo().addObserver(menuDown.getBarPluit());
        PrixMarche.getInstance().addObserver(topMenu);
        this.jardinModel.getMeteo().addObserver(background);
        this.jardinModel.getMeteo().addObserver(menuDown);
        this.joueurModel.addObserver(menuDown.getExpBar());
    }

    private void initArgentLabel() {
        this.menuDown.getExpBar().updateArgentLabel(String.valueOf(this.joueurModel.getSolde()));
    }
}