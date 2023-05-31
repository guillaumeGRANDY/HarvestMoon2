package org.polytech.vue;

import org.polytech.model.legume.type.TypeLegume;
import org.polytech.utils.ExtensionImage;
import org.polytech.utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static java.awt.GridBagConstraints.LINE_START;

public class TopMenuItem extends JPanel {

    private Image image;
    private Image backgroundImage;
    private JLabel labelImage;
    private JLabel nb;

    public TopMenuItem(TypeLegume typeLegume){
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

        constraints.gridx=0;
        constraints.gridy=1;
        constraints.insets=new Insets(5,10,5,10);
        JPanel etiquette=new JPanel() {

            private Image backgroundImage=Utils.getImageIconFromResources("etiquette", ExtensionImage.PNG).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                this.setOpaque(false);
                g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        this.add(etiquette,constraints);

        nb=new JLabel("000");
        nb.setFont(new Font("Arial", Font.BOLD, 28));
        nb.setBorder(new EmptyBorder(0,10,0,0));
        nb.setForeground(new java.awt.Color(92, 68, 25));
        etiquette.add(nb,BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
