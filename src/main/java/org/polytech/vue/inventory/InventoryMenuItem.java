package org.polytech.vue.inventory;

import org.polytech.model.inventory.LegumeInventoryItem;
import org.polytech.utils.ExtensionImage;
import org.polytech.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class InventoryMenuItem extends JPanel implements Observer, MouseListener {
    private final Image backgroundImage;
    private final InventoryMenu inventoryMenu;
    private LegumeInventoryItem legumeInventoryItem = null;
    private JLabel backgroundLabel;
    private JLabel labelImageContainingLegumeImage;
    private Image imageOfTypeLegume;
    private JLabel nbTypeLegumeJpanel;

    public InventoryMenuItem(InventoryMenu inventoryMenu, LegumeInventoryItem legumeInventoryItem) {
        this.inventoryMenu = inventoryMenu;

        this.legumeInventoryItem = legumeInventoryItem;
        this.legumeInventoryItem.addObserver(this);

        this.backgroundImage = Utils.getImageIconFromResources("caseCoffre", ExtensionImage.PNG).getImage();
        ImageIcon backgroundImageIcon = new ImageIcon(this.backgroundImage.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        backgroundLabel = new JLabel(backgroundImageIcon);
        backgroundLabel.setBounds(0, 0, backgroundImageIcon.getIconHeight(), backgroundImageIcon.getIconWidth());
        this.setOpaque(false);

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints=new GridBagConstraints();
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.insets=new Insets(10,10,0,10);

        setImageIconForTypeLegume();
        this.add(labelImageContainingLegumeImage, constraints);

        constraints.gridy = 1;
        this.setLabelOfTotalLegumes();
        nbTypeLegumeJpanel.setForeground(Color.WHITE);
        constraints.insets=new Insets(5,0,5,0);
        this.add(nbTypeLegumeJpanel, constraints);

        try {
            Font minecraftFont = Font.createFont(Font.TRUETYPE_FONT, new File("./src/font/Minecraft.otf"));
            nbTypeLegumeJpanel.setFont(minecraftFont.deriveFont(Font.PLAIN, 18));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.addMouseListener(this);
    }

    private void setImageIconForTypeLegume() {
        this.imageOfTypeLegume = Utils.getImageIconFromResources(this.legumeInventoryItem.getTypeLegume().getImageName(), ExtensionImage.PNG).getImage();
        ImageIcon icon = new ImageIcon(this.imageOfTypeLegume.getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        labelImageContainingLegumeImage = new JLabel(icon);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof LegumeInventoryItem inventoryItem) {
            if(this.legumeInventoryItem.getTypeLegume().equals(inventoryItem.getTypeLegume())) {
                this.nbTypeLegumeJpanel.setText(String.valueOf((int) (arg)));
                this.revalidate();
                this.repaint();
            }
        }
    }

    private void setLabelOfTotalLegumes() {
        nbTypeLegumeJpanel = new JLabel(String.valueOf(this.legumeInventoryItem.getNumber()));
        nbTypeLegumeJpanel.setBounds(50, 50, 20, 20);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.inventoryMenu.handleEvent(this.legumeInventoryItem);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.inventoryMenu.handleEvent(this.legumeInventoryItem);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
