package org.polytech.vue.inventory;

import org.polytech.model.JoueurModel;
import org.polytech.model.inventory.LegumeInventoryItem;
import org.polytech.utils.ExtensionImage;
import org.polytech.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class InventoryMenu extends JPanel implements BuyInventoryItemEvent {
    private Image backgroundImage;

    private final JoueurModel joueurModel;

    public InventoryMenu(JoueurModel joueurModel) {
        this.joueurModel = joueurModel;
        this.backgroundImage = Utils.getImageIconFromResources("coffre", ExtensionImage.PNG).getImage();
        this.setOpaque(false);

        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        GridBagConstraints constraints = new GridBagConstraints();

        int i=0;
        for (LegumeInventoryItem legumeInventoryItem : this.joueurModel.getInventory().inventoryItems()) {
            constraints.gridx=i%4;
            constraints.gridy=(int)i/4;
            this.add(new InventoryMenuItem(this, legumeInventoryItem), constraints);
            i++;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void handleEvent(LegumeInventoryItem legumeInventoryItem) {
        if(legumeInventoryItem.canSellLegumes()) {
            if(JOptionPane.showConfirmDialog(this, "Êtes vous-sûr de vouloir vendre " + legumeInventoryItem.getNumber() + " de" + legumeInventoryItem.getTypeLegume().getName()) == JOptionPane.OK_OPTION) {
                joueurModel.sell(legumeInventoryItem);
            }
        }
    }
}
