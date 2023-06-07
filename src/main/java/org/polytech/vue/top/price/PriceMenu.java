package org.polytech.vue.top.price;

import org.polytech.model.PrixMarche;
import org.polytech.model.legume.type.TypeLegume;
import org.polytech.vue.top.SpeedMenu;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class PriceMenu extends JPanel implements Observer {

    PriceMenuItem articles[];

    public PriceMenu() {

        this.setBackground(new java.awt.Color(0, 0, 0,0.5F));

        articles=new PriceMenuItem[8];
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints=new GridBagConstraints();

        constraints.weightx = 1;
        constraints.weighty = 0.1; // To allocate space in the vertical direction

        int i=0;
        constraints.insets=new Insets(10,5,10,5);
        for (TypeLegume typeLegume : TypeLegume.values()) {
            constraints.gridx=i;
            articles[i] = new PriceMenuItem(typeLegume);
            this.add(articles[i],constraints);
            i++;
        }

        SpeedMenu speedMenu=new SpeedMenu();
        constraints.gridx = 9; // To make sure it starts at the left
        constraints.gridy = 0; // To put it at the top
        constraints.weightx = 0.1;
        constraints.weighty = 0.1; // To allocate space in the vertical direction

        this.add(speedMenu, constraints);

    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof PrixMarche)
        {
            for(int i=0;i<this.articles.length;i++)
            {
                if(this.articles[i]!=null) {
                    this.articles[i].changePrice(((PrixMarche) o).getPrice(this.articles[i].getLegumeType()));
                }
            }
        }
    }
}
