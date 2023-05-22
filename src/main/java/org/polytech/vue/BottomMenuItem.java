package org.polytech.vue;

import org.polytech.utils.ExtensionImage;
import org.polytech.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class BottomMenuItem extends JPanel {

    Image image;
    Image backgroundImage;

    JLabel labelImage;

    JardinVue parent;

    public BottomMenuItem() {
    }

    public BottomMenuItem(String image){
        this.parent=parent;
        this.backgroundImage= Utils.getImageFromResources("case", ExtensionImage.PNG).getImage();

        this.image = Utils.getImageFromResources(image, ExtensionImage.PNG).getImage();
        ImageIcon icon = new ImageIcon(this.image.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        labelImage=new JLabel(icon);

        // Configuration des contraintes pour centrer l'image
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;

        this.add(labelImage, constraints);
    }

    public void resizeImage()
    {
        int dimension=Math.min(getWidth(),getHeight())-100;
        ImageIcon icon = new ImageIcon(image.getScaledInstance(dimension, dimension, Image.SCALE_DEFAULT));
        labelImage.setIcon(icon);
        parent.repaint();
        labelImage.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
