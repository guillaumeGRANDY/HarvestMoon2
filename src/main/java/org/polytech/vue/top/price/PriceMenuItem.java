package org.polytech.vue.top.price;

import org.polytech.model.legume.type.TypeLegume;
import org.polytech.utils.ExtensionImage;
import org.polytech.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class PriceMenuItem extends JPanel {

    private Image image;
    private Image backgroundImage;
    private JLabel labelImage;
    private JLabel nb;


    private TypeLegume legumeType;

    public PriceMenuItem(TypeLegume typeLegume){
        this.legumeType=typeLegume;
        this.setOpaque(false);
        this.backgroundImage= Utils.getImageIconFromResources("panneau", ExtensionImage.PNG).getImage();

        this.image = Utils.getImageIconFromResources(typeLegume.getImageName(), ExtensionImage.PNG).getImage();
        ImageIcon icon = new ImageIcon(this.image.getScaledInstance(70, 70, Image.SCALE_DEFAULT));

        this.setLayout(new GridBagLayout());

        // Configuration des contraintes pour centrer l'image
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets=new Insets(30,10,5,10);
        constraints.fill=GridBagConstraints.HORIZONTAL;

        labelImage=new JLabel(icon);
        this.add(labelImage, constraints);

        nb=new JLabel("000");
        nb.setFont(new Font("Arial", Font.BOLD, 28));
        nb.setForeground(new java.awt.Color(92, 68, 25));

        Font minecraftFont = Utils.getFontFromResources("Minecraft");
        nb.setFont(minecraftFont.deriveFont(Font.PLAIN, 28));

        constraints.gridx=0;
        constraints.gridy=1;
        constraints.insets=new Insets(5,10,5,10);
        constraints.fill=GridBagConstraints.CENTER;
        this.add(nb,constraints);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void changePrice(int price)
    {
        if(price!=-1)
        {
            this.nb.setText(Integer.toString(price));
            this.repaint();
        }
    }

    public TypeLegume getLegumeType() {
        return legumeType;
    }
}
