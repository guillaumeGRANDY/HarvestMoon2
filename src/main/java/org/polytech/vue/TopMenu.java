package org.polytech.vue;

import org.polytech.model.legume.type.TypeLegume;

import javax.swing.*;
import java.awt.*;

public class TopMenu extends JPanel {

    TopMenuItem articles[];

    public TopMenu() {

        this.setBackground(new java.awt.Color(0, 0, 0,0.5F));

        articles=new TopMenuItem[8];
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints=new GridBagConstraints();

        int i=0;
        constraints.insets=new Insets(10,10,10,10);
        for (TypeLegume typeLegume : TypeLegume.values()) {
            constraints.gridy=i;
            articles[i] = new TopMenuItem(typeLegume);
            this.add(articles[i],constraints);
        }
    }
}
