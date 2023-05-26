package org.polytech.vue;

import org.polytech.utils.ExtensionImage;
import org.polytech.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ExpBar extends JPanel{

    ImageIcon backgroundImage;

    public ExpBar()
    {
        backgroundImage= Utils.getImageFromResources("barExp", ExtensionImage.PNG);
        //Label de l'argent
        JLabel lblArgent = new JLabel("XXXX");
        lblArgent.setHorizontalAlignment(JLabel.LEFT);
        lblArgent.setFont(new Font("Arial", Font.PLAIN,20 ));
        lblArgent.setForeground(Color.black);
        lblArgent.setOpaque(false);
        this.add(lblArgent);

        try {
            Font minecraftFont = Font.createFont(Font.TRUETYPE_FONT, new File("./src/font/Minecraft.otf"));
            lblArgent.setFont(minecraftFont.deriveFont(Font.PLAIN, 40));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0,this.getHeight()/2,  this.getWidth(), 15, this);
    }

}
