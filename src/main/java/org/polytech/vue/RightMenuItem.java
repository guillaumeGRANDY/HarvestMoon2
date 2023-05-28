package org.polytech.vue;

import org.polytech.utils.ExtensionImage;
import org.polytech.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class RightMenuItem extends JPanel {
    private Image backgroundImage;
    private RightMenu parent;

    public RightMenuItem(RightMenu parent) {
        this.backgroundImage = Utils.getImageIconFromResources("caseCoffre", ExtensionImage.PNG).getImage();
        this.setOpaque(false);
        this.parent=parent;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(140,140);
    }
}
