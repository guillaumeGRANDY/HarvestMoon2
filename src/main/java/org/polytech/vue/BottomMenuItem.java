package org.polytech.vue;

import org.polytech.model.legume.type.TypeLegume;
import org.polytech.utils.ExtensionImage;
import org.polytech.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class BottomMenuItem extends JPanel {

    private Image image;
    private Image backgroundImage;
    private JLabel labelImage;

    private TypeLegume typeLegume;

    public BottomMenuItem(TypeLegume typeLegume){
        this.typeLegume = typeLegume;
        this.backgroundImage= Utils.getImageIconFromResources("case", ExtensionImage.PNG).getImage();

        this.image = Utils.getImageIconFromResources(typeLegume.getImageName(), ExtensionImage.PNG).getImage();
        ImageIcon icon = new ImageIcon(this.image.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        labelImage=new JLabel(icon);

        // Configuration des contraintes pour centrer l'image
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;

        this.add(labelImage, constraints);
    }

    public TypeLegume getTypeLegume() {
        return typeLegume;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void setBackgroundImage(String image) {
        this.backgroundImage = Utils.getImageIconFromResources(image, ExtensionImage.PNG).getImage();
        this.repaint();
    }
}