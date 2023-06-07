package org.polytech.vue.bottom.meteo;

import org.polytech.model.Meteo;
import org.polytech.utils.ExtensionImage;
import org.polytech.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class BarSoleil extends JPanel implements Observer {

    JLabel bar[];
    ImageIcon sun;
    ImageIcon noSun;

    public BarSoleil() {
        GridLayout layout = new GridLayout(1, 10,0,2);
        this.setLayout(layout);

        this.bar = new JLabel[10];
        for(int i=0;i<10;i++)
        {
            this.bar[i]=new JLabel();
            this.bar[i].setBackground(new java.awt.Color(0, 0, 0, 0));
            this.add(this.bar[i]);
        }

        this.setBackground(new java.awt.Color(0, 0, 0, 0));

        sun= new ImageIcon(Utils.getImageFromResources("sun", ExtensionImage.PNG).getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        noSun= new ImageIcon(Utils.getImageFromResources("noSun", ExtensionImage.PNG).getScaledInstance(25, 25, Image.SCALE_DEFAULT));

        for(int i=0;i<10;i++)
        {
            this.bar[i].setIcon(sun);
        }
    }

    public void actualiseSun(int value) {

        if (value > 0){
            this.bar[value - 1].setIcon(sun);
            this.bar[value - 1].repaint();

            if (value > 1) {
                this.bar[value - 2].setIcon(sun);
                this.bar[value - 2].repaint();

                if (value > 2) {
                    this.bar[value - 3].setIcon(sun);
                    this.bar[value - 3].repaint();
                }
            }
        }

        if(value<10) {
            this.bar[value].setIcon(noSun);
            this.bar[value].repaint();

            if(value<9) {
                this.bar[value + 1].setIcon(noSun);
                this.bar[value + 1].repaint();

                if(value<8) {
                    this.bar[value + 2].setIcon(noSun);
                    this.bar[value + 2].repaint();
                }
            }
        }
        this.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Meteo m)
        {
            actualiseSun(m.getSoleil());
        }
    }
}
