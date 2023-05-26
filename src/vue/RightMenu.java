package vue;

import javax.swing.*;
import java.awt.*;

public class RightMenu extends JPanel {
    private Image backgroundImage;

    public RightMenu() {
        this.backgroundImage = new ImageIcon("./src/img/coffre.png").getImage();
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
