package org.polytech.vue;

import org.polytech.model.legume.type.TypeLegume;
import org.polytech.utils.ExtensionImage;
import org.polytech.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class RightMenuItem extends JPanel {
    private Image backgroundImage;
    private RightMenu parent;

    private JLabel labelImage;
    private Image image;
    private JLabel nb;



    public RightMenuItem(RightMenu parent, TypeLegume typeLegume) {
        this.backgroundImage = Utils.getImageIconFromResources("caseCoffre", ExtensionImage.PNG).getImage();
        this.setOpaque(false);
        this.parent=parent;

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints=new GridBagConstraints();

        this.image = Utils.getImageIconFromResources(typeLegume.getImageName(), ExtensionImage.PNG).getImage();
        ImageIcon icon = new ImageIcon(this.image.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        constraints.gridx=0;
        constraints.gridy=0;
        labelImage=new JLabel(icon);
        this.add(labelImage,constraints);

        nb=new JLabel("0");
        constraints.gridx=0;
        constraints.gridy=1;
        this.add(nb,constraints);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
