package org.polytech.vue;

import org.polytech.model.legume.Meteo;
import org.polytech.utils.ExtensionImage;
import org.polytech.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Background extends JPanel implements Observer{
    private Image image;

    public Background() {
        this.image = Utils.getImageIconFromResources("jour", ExtensionImage.PNG).getImage();
        this.setOpaque(false);
    }

    public void actauliseBackground(int hour)
    {
        if(hour>=22 || hour<=3)
        {
            this.image=Utils.getImageIconFromResources("nuit", ExtensionImage.PNG).getImage();
            System.out.println("nuit");
        }
        else if(hour>=4 && hour<=9)
        {
            this.image=Utils.getImageIconFromResources("matin", ExtensionImage.PNG).getImage();
            System.out.println("matin");
        }
        else if(hour>=10 && hour<=15)
        {
            this.image=Utils.getImageIconFromResources("jour", ExtensionImage.PNG).getImage();
            System.out.println("jour");
        }
        else if(hour>=16 && hour<=21)
        {
            this.image=Utils.getImageIconFromResources("soir", ExtensionImage.PNG).getImage();
            System.out.println("soir");
        }
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Meteo m)
        {
            actauliseBackground(m.getHeure());
        }
    }
}
