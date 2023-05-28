package org.polytech.vue;

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
        GridBagConstraints constraints=new GridBagConstraints();

        JLabel lblInventaire = new JLabel("Mon iventaire");
        lblInventaire.setHorizontalAlignment(JLabel.LEFT);
        lblInventaire.setFont(new Font("Arial", Font.PLAIN,50 ));
        lblInventaire.setForeground(Color.black);
        lblInventaire.setOpaque(false);

        constraints.gridy=0;
        constraints.weighty=1;
        constraints.insets=new Insets(30,30,30,30);
        constraints.anchor=GridBagConstraints.PAGE_START;
        this.add(lblInventaire,constraints);

        try {
            Font minecraftFont = Font.createFont(Font.TRUETYPE_FONT, new File("./src/font/Minecraft.otf"));
            lblInventaire.setFont(minecraftFont.deriveFont(Font.PLAIN, 50));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JPanel panelInventaire=new JPanel();
        GridLayout layout=new GridLayout(3,3);
        panelInventaire.setLayout(layout);

        chestItem=new RightMenuItem[9];
        for(int i=0;i<9;i++)
        {
            chestItem[i]=new RightMenuItem(this);
            panelInventaire.add(chestItem[i]);
        }

        constraints.gridy = 1;
        constraints.weighty=0;
        this.add(panelInventaire,constraints);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
