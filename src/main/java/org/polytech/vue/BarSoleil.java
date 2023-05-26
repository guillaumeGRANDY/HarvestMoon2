package org.polytech.vue;

import org.polytech.utils.ExtensionImage;
import org.polytech.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class BarSoleil extends JPanel {

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

        sun= new ImageIcon(Utils.getImageFromResources("sun", ExtensionImage.PNG).getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        noSun= new ImageIcon(Utils.getImageFromResources("noSun", ExtensionImage.PNG).getScaledInstance(20, 20, Image.SCALE_DEFAULT));

        actualiseSun(5);
    }

    public void actualiseSun(int value)
    {
        for(int i=0;i<10;i++)
        {
            if(i<value)
            {
                this.bar[i].setIcon(sun);
            }
            else {
                this.bar[i].setIcon(noSun);
            }
        }
        this.repaint();
    }
}
