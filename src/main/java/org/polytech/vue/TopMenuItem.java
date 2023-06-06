package org.polytech.vue;

import org.polytech.model.legume.type.TypeLegume;
import org.polytech.utils.ExtensionImage;
import org.polytech.utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.awt.GridBagConstraints.LINE_START;

public class TopMenuItem extends JPanel {

    private Image image;
    private Image backgroundImage;
    private JLabel labelImage;
    private JLabel nb;


    private TypeLegume legumeType;

    public TopMenuItem(TypeLegume typeLegume){
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

        try {
            Font minecraftFont = Font.createFont(Font.TRUETYPE_FONT, new File("./src/font/Minecraft.otf"));
            nb.setFont(minecraftFont.deriveFont(Font.PLAIN, 28));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        constraints.gridx=0;
        constraints.gridy=1;
        constraints.insets=new Insets(5,10,5,10);

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
