package org.polytech.vue;

import org.polytech.model.legume.TypeLegume;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.awt.GridBagConstraints.LINE_START;

public class BottomMenu extends JPanel implements MouseListener {
    private final ExpBar expBar;

    private BottomMenuItem selectedBottomItem;

    public BottomMenuItem getSelectedBottomItem() {
        return selectedBottomItem;
    }

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

        expBar=new ExpBar();
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

        buttonCactus=new BottomMenuItem("cactus", TypeLegume.TOMATE);
        buttonCactus.addMouseListener(this);
        casePanel.add(buttonCactus);

        buttonFleurViolet=new BottomMenuItem("fleurViolet", TypeLegume.TOMATE);
        buttonFleurViolet.addMouseListener(this);
        casePanel.add(buttonFleurViolet);

        BottomMenuItem buttonFleurOrange=new BottomMenuItem("fleurOrange", TypeLegume.TOMATE);
        buttonFleurOrange.addMouseListener(this);
        casePanel.add(buttonFleurOrange);

        BottomMenuItem buttonFleurBleu=new BottomMenuItem("fleurBleu", TypeLegume.TOMATE);
        buttonFleurBleu.addMouseListener(this);
        casePanel.add(buttonFleurBleu);

        BottomMenuItem buttonFleurRouge=new BottomMenuItem("fleurRouge", TypeLegume.TOMATE);
        buttonFleurRouge.addMouseListener(this);
        casePanel.add(buttonFleurRouge);

        BottomMenuItem buttonFleurJaune=new BottomMenuItem("fleurJaune", TypeLegume.TOMATE);
        buttonFleurJaune.addMouseListener(this);
        casePanel.add(buttonFleurJaune);

        BottomMenuItem buttonChampignonMarron=new BottomMenuItem("champignonMarron", TypeLegume.TOMATE);
        buttonChampignonMarron.addMouseListener(this);
        casePanel.add(buttonChampignonMarron);

        BottomMenuItem buttonChampignonRouge=new BottomMenuItem("champignonRouge", TypeLegume.TOMATE);
        buttonChampignonRouge.addMouseListener(this);
        casePanel.add(buttonChampignonRouge);
    }

    public BarSoleil getBarSoleil() {
        return barSoleil;
    }

    public ExpBar getExpBar() {
        return expBar;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouse click");

        //enleve l'image de fond spécial
        if(selectedBottomItem !=null)
        {
            selectedBottomItem.setBackgroundImage("case");
        }

        //ajoute l'image de fond spécial
        ((BottomMenuItem) e.getSource()).setBackgroundImage("caseSelectionne");
        selectedBottomItem =((BottomMenuItem) e.getSource());
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
