package org.polytech.model.inventory;

import org.polytech.model.legume.LegumeModel;
import org.polytech.model.legume.type.TypeLegume;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public class Inventory extends Observable implements Serializable {
    private final HashMap<TypeLegume, LegumeInventoryItem> legumesWithQuantity;

    public Inventory() {
        legumesWithQuantity = new HashMap<>();
        for (TypeLegume typeLegume : TypeLegume.values()) {
            legumesWithQuantity.put(typeLegume, new LegumeInventoryItem(typeLegume));
        }
    }

    public List<LegumeInventoryItem> inventoryItems() {
        return new ArrayList<>(legumesWithQuantity.values());
    }

    public void addLegume(LegumeModel legume) {
        LegumeInventoryItem legumeInventoryItemToUpdate = this.legumesWithQuantity.get(legume.getType());
        legumeInventoryItemToUpdate.addNewLegume(legume);
        this.setChanged();
        this.notifyObservers(legumeInventoryItemToUpdate);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for(LegumeInventoryItem legume : this.legumesWithQuantity.values()) {
            str.append(legume.getTypeLegume());
            str.append(": ");
            str.append(legume.getNumber());
            str.append("\n");
        }
        return str.toString();
    }
}