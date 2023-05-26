package org.polytech.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import static java.awt.GridBagConstraints.LINE_START;

public class BottomMenu extends JPanel implements MouseListener {
    private BottomMenuItem SelectedBotton;

    private BottomMenuItem buttonCactus;
    private BottomMenuItem buttonFleurViolet;

    private BarSoleil barSoleil;

    public BottomMenu() {

        this.setBackground(new java.awt.Color(0, 0, 0, 0));

        GridLayout menuLayout = new GridLayout(1, 7,0,0);
        this.setLayout(menuLayout);

        GridBagLayout bottomMenLayout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        this.setLayout(bottomMenLayout);

        barSoleil=new BarSoleil();
        constraints.gridx = 0; // To make sure it starts at the left
        constraints.gridy = 0; // To put it at the top
        constraints.gridwidth = 1;
        constraints.anchor=LINE_START;
        this.add(barSoleil,constraints);

        ExpBar expBar=new ExpBar();
        expBar.setPreferredSize(new Dimension(800, 15));
        expBar.setBackground(new java.awt.Color(0, 0, 0, 0));
        constraints.gridx = 0; // To make sure it starts at the left
        constraints.gridy = 1; // To put it at the top
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(expBar,constraints);

        JPanel casePanel=new JPanel();
        GridLayout caseLayout = new GridLayout(1, 7);
        casePanel.setLayout(caseLayout);

        constraints.gridx = 0; // To make sure it starts at the left
        constraints.gridy = 2; // To put it at the top
        constraints.gridwidth = 1;
        this.add(casePanel,constraints);

        buttonCactus=new BottomMenuItem("cactus");
        buttonCactus.addMouseListener(this);
        casePanel.add(buttonCactus);

        buttonFleurViolet=new BottomMenuItem("fleurViolet");
        buttonFleurViolet.addMouseListener(this);
        casePanel.add(buttonFleurViolet);

        BottomMenuItem buttonFleurOrange=new BottomMenuItem("fleurOrange");
        buttonFleurOrange.addMouseListener(this);
        casePanel.add(buttonFleurOrange);

        BottomMenuItem buttonFleurBleu=new BottomMenuItem("fleurBleu");
        buttonFleurBleu.addMouseListener(this);
        casePanel.add(buttonFleurBleu);

        BottomMenuItem buttonFleurRouge=new BottomMenuItem("fleurRouge");
        buttonFleurRouge.addMouseListener(this);
        casePanel.add(buttonFleurRouge);

        BottomMenuItem buttonFleurJaune=new BottomMenuItem("fleurJaune");
        buttonFleurJaune.addMouseListener(this);
        casePanel.add(buttonFleurJaune);

        BottomMenuItem buttonChampignonMarron=new BottomMenuItem("champignonMarron");
        buttonChampignonMarron.addMouseListener(this);
        casePanel.add(buttonChampignonMarron);

        BottomMenuItem buttonChampignonRouge=new BottomMenuItem("champignonRouge");
        buttonChampignonRouge.addMouseListener(this);
        casePanel.add(buttonChampignonRouge);
    }

    public BarSoleil getBarSoleil() {
        return barSoleil;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println("mouse click");

        //enleve l'image de fond spécial
        if(SelectedBotton!=null)
        {
            SelectedBotton.setBackgroundImage("case");
        }

        //ajoute l'image de fond spécial
        ((BottomMenuItem) e.getSource()).setBackgroundImage("caseSelectionne");
        SelectedBotton=((BottomMenuItem) e.getSource());
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

}
