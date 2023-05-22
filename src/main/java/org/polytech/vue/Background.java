package org.polytech.vue;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {
    private Image image;

    public Background() {
        this.image = new ImageIcon("./src/main/java/org/polytech/img/minecraftDirt.png").getImage();
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
