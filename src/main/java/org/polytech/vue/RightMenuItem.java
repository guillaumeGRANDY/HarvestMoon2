package org.polytech.vue;

import org.polytech.model.inventory.LegumeInventoryItem;
import org.polytech.model.legume.type.TypeLegume;
import org.polytech.utils.ExtensionImage;
import org.polytech.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class RightMenuItem extends JPanel implements Observer {
    private final TypeLegume typeLegume;
    private Image backgroundImage;
    private RightMenu parent;

    private JLabel labelImageContainingLegumeImage;
    private Image imageOfTypeLegume;
    private JLabel nbTypeLegumeJpanel;



    public RightMenuItem(RightMenu parent, TypeLegume typeLegume) {
        this.backgroundImage = Utils.getImageIconFromResources("caseCoffre", ExtensionImage.PNG).getImage();
        this.setOpaque(false);
        this.parent=parent;
        this.parent.getJardinVue().getJoueurModel().getInventory().addObserver(this);
        this.typeLegume = typeLegume;

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints=new GridBagConstraints();

        setImageIconForTypeLegume();
        constraints.gridx=0;
        constraints.gridy=0;

        this.add(labelImageContainingLegumeImage,constraints);

        nbTypeLegumeJpanel =new JLabel("0");
        constraints.gridx=0;
        constraints.gridy=1;
        this.add(nbTypeLegumeJpanel,constraints);
    }

    private void setImageIconForTypeLegume() {
        this.imageOfTypeLegume = Utils.getImageIconFromResources(typeLegume.getImageName(), ExtensionImage.PNG).getImage();
        ImageIcon icon = new ImageIcon(this.imageOfTypeLegume.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        labelImageContainingLegumeImage =new JLabel(icon);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof LegumeInventoryItem legumeInventoryItem) {
            TypeLegume typeLegumeUpdated = legumeInventoryItem.getTypeLegume();
            if(typeLegumeUpdated.equals(this.typeLegume)) {
                nbTypeLegumeJpanel.setText(String.valueOf(legumeInventoryItem.getNumber()));
            }
        }
    }
}
