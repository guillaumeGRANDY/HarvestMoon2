package org.polytech.vue;

import org.polytech.model.legume.Meteo;
import org.polytech.utils.ExtensionImage;
import org.polytech.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class BarPluit extends JPanel implements Observer {

    JLabel bar[];
    ImageIcon rain;
    ImageIcon noRain;

    public BarPluit() {
        GridLayout layout = new GridLayout(1, 10,0,2);
        this.setLayout(layout);

        this.bar = new JLabel[10];
        for(int i=0;i<10;i++)
        {
            this.bar[i]=new JLabel();
            this.bar[i].setBackground(new Color(0, 0, 0, 0));
            this.add(this.bar[i]);
        }

        this.setBackground(new Color(0, 0, 0, 0));

        rain= new ImageIcon(Utils.getImageFromResources("rain", ExtensionImage.PNG).getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        noRain= new ImageIcon(Utils.getImageFromResources("noRain", ExtensionImage.PNG).getScaledInstance(20, 20, Image.SCALE_DEFAULT));

        for(int i=0;i<10;i++)
        {
            if(i<5)
            {
                this.bar[i].setIcon(rain);
            }
            else {
                this.bar[i].setIcon(noRain);
            }
        }
    }

    public void actualiseRain(int value)
    {
        this.bar[value].setIcon(rain);

        if(value<9) {
            this.bar[value + 1].setIcon(noRain);
        }
        this.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Meteo m)
        {
            actualiseRain(m.getPluit());
        }
    }
}
