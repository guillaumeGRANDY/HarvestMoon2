package org.polytech.vue;

import org.polytech.model.PrixMarche;
import org.polytech.model.legume.type.TypeLegume;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class TopMenu extends JPanel implements Observer {

    TopMenuItem articles[];

    public TopMenu() {

        this.setBackground(new java.awt.Color(0, 0, 0,0.5F));

        articles=new TopMenuItem[8];
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints=new GridBagConstraints();

        int i=0;
        constraints.insets=new Insets(10,5,10,5);
        for (TypeLegume typeLegume : TypeLegume.values()) {
            constraints.gridx=i;
            articles[i] = new TopMenuItem(typeLegume);
            this.add(articles[i],constraints);
            i++;
        }
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
