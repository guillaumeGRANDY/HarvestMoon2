package org.polytech.vue;

import org.polytech.model.JardinModel;
import org.polytech.model.Ordonnanceur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

public class JardinVue extends JFrame {
    private final JardinModel jardinModel;

    public JardinVue(JardinModel model) {
        super();
        this.jardinModel = model;

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                revalidate();
            }
        });

        initialize();
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
        bandeauSup.setLayout(new GridLayout(1, 2));
        bandeauSup.setPreferredSize(new Dimension(800, 200));

        constraints.weightx = 1.0;
        constraints.weighty = 0.1; // To allocate space in the vertical direction
        constraints.fill = GridBagConstraints.HORIZONTAL; // To make it fill the horizontal space
        constraints.gridx = 0; // To make sure it starts at the left
        constraints.gridy = 0; // To put it at the top
        constraints.gridwidth = 3; // To make it extend across all columns
        background.add(bandeauSup, constraints);

        //Label de la date
        JLabel lblDate = new JLabel("Date Inconnu");
        lblDate.setHorizontalAlignment(JLabel.CENTER);
        lblDate.setFont(new Font("Arial", Font.PLAIN, 35));
        lblDate.setForeground(Color.WHITE);
        bandeauSup.add(lblDate);

        //Label pour l'argent
        JLabel lblArgent = new JLabel("Argent: Inconnu");
        lblArgent.setHorizontalAlignment(JLabel.CENTER);
        lblArgent.setFont(new Font("Arial", Font.PLAIN, 35));
        lblArgent.setForeground(Color.WHITE);
        bandeauSup.add(lblArgent, BorderLayout.EAST);

        //créer le jardin
        JPanel zoneJardin = new JPanel();
        zoneJardin.setBackground(new java.awt.Color(0, 0, 0, 0));
        GridLayout jardinLayout = new GridLayout(4, 6, 10, 10);
        zoneJardin.setLayout(jardinLayout);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
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
        RightMenu menuLateral = new RightMenu();
        menuLateral.setBackground(new java.awt.Color(0, 0, 0, 0.5F));

        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0; // To allocate more space to this component
        background.add(menuLateral, constraints);

        JButton buttonRecolte = new JButton("Récolter");
        buttonRecolte.setBackground(new java.awt.Color(46, 171, 0));
        buttonRecolte.setForeground(Color.white);
        menuLateral.add(buttonRecolte);

        //créer le menu inférieur
        BottomMenu menuDown = new BottomMenu();

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.weightx = 1.0;
        constraints.weighty = 0.1; // Réduire le poids pour allouer moins d'espace
        constraints.fill = GridBagConstraints.HORIZONTAL;
        background.add(menuDown, constraints);

        setVisible(true);
        Ordonnanceur.getInstance().start();
    }
}