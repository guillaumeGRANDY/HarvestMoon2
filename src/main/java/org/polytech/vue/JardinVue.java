package org.polytech.vue;

import org.polytech.model.JardinModel;
import org.polytech.model.JoueurModel;
import org.polytech.model.Ordonnanceur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.io.File;
import java.io.IOException;

public class JardinVue extends JFrame {
    private final JardinModel jardinModel;
    private final JoueurModel joueurModel;

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
        this.setSize(800, 600);
        this.setTitle("HarvestMoon2");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Background background = new Background();
        GridBagLayout windowLayout = new GridBagLayout();
        background.setLayout(windowLayout);
        this.add(background);

        // Création des contraintes GridBagConstraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        //créer le bandeau supérieur
        JPanel bandeauSup = new JPanel();
        bandeauSup.setBackground(new java.awt.Color(0, 0, 0, 0));
        bandeauSup.setLayout(new GridLayout(2, 1));
        bandeauSup.setPreferredSize(new Dimension(800, 200));

        constraints.weightx = 1.0;
        constraints.weighty = 0.1; // To allocate space in the vertical direction
        constraints.fill = GridBagConstraints.HORIZONTAL; // To make it fill the horizontal space
        constraints.gridx = 0; // To make sure it starts at the left
        constraints.gridy = 0; // To put it at the top
        constraints.gridwidth = 1; // To make it extend across all columns
        background.add(bandeauSup, constraints);

        SpeedMenu speedMenu=new SpeedMenu();
        constraints.gridx = 1; // To make sure it starts at the left
        constraints.gridy = 0; // To put it at the top
        constraints.weightx = 0.1;
        constraints.weighty = 0.1; // To allocate space in the vertical direction
        background.add(speedMenu, constraints);



        //Label de la date
        JLabel lblDate = new JLabel("Date Inconnue");
        lblDate.setHorizontalAlignment(JLabel.LEFT);
        lblDate.setFont(new Font("Arial", Font.PLAIN, 35));
        lblDate.setBackground(new java.awt.Color(0, 0, 0, 0.3F));
        lblDate.setForeground(Color.WHITE);
        lblDate.setOpaque(true);
        bandeauSup.add(lblDate);

        //Label de la soleil
        JLabel lblMeteo = new JLabel("Meteo");
        lblMeteo.setHorizontalAlignment(JLabel.LEFT);
        lblMeteo.setFont(new Font("Arial", Font.PLAIN, 35));
        lblMeteo.setBackground(new java.awt.Color(0, 0, 0, 0.3F));
        lblMeteo.setForeground(Color.WHITE);
        lblMeteo.setOpaque(true);
        bandeauSup.add(lblMeteo);

        try {
            Font minecraftFont = Font.createFont(Font.TRUETYPE_FONT, new File("./src/font/Minecraft.otf"));
            lblDate.setFont(minecraftFont.deriveFont(Font.PLAIN, 35));
            lblMeteo.setFont(minecraftFont.deriveFont(Font.PLAIN, 35));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //créer le jardin
        JPanel zoneJardin = new JPanel();
        zoneJardin.setBackground(new java.awt.Color(0, 0, 0, 0));
        GridLayout jardinLayout = new GridLayout(5, 8, 10, 10);
        zoneJardin.setLayout(jardinLayout);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
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
        RightMenu menuLateral = new RightMenu(this);
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

        this.jardinModel.getMeteo().addObserver(menuDown.getBarSoleil());
        this.jardinModel.getMeteo().addObserver(menuDown.getBarPluit());
        this.jardinModel.getMeteo().addObserver(background);
        this.jardinModel.getMeteo().addObserver(menuDown);
        this.joueurModel.addObserver(menuDown.getExpBar());
        initArgentLabel();

        setVisible(true);
        Ordonnanceur.getInstance().start();
    }

    private void initArgentLabel() {
        this.menuDown.getExpBar().updateArgentLabel(String.valueOf(this.joueurModel.getSolde()));
    }
}