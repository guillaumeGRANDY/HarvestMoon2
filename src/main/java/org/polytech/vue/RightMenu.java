package org.polytech.vue;

import org.polytech.model.legume.type.TypeLegume;
import org.polytech.utils.ExtensionImage;
import org.polytech.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RightMenu extends JPanel {
    private Image backgroundImage;
    private RightMenuItem chestItem[];

    public RightMenu() {
        this.backgroundImage = Utils.getImageIconFromResources("coffre", ExtensionImage.PNG).getImage();
        this.setOpaque(false);

        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        GridBagConstraints constraints = new GridBagConstraints();
        //constraints.insets=new Insets(10,10,10,10);

        chestItem=new RightMenuItem[8];
        int i=0;
        for (TypeLegume typeLegume : TypeLegume.values()) {
                constraints.gridx=i%4;
                constraints.gridy=(int)i/4;
                chestItem[i] = new RightMenuItem(this,typeLegume);
                this.add(chestItem[i],constraints);
                i++;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
