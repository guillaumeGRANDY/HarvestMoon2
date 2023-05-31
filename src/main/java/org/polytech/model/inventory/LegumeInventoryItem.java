package org.polytech.model.inventory;

import org.polytech.model.legume.LegumeModel;
import org.polytech.model.legume.type.TypeLegume;

public class LegumeInventoryItem {
    private LegumeModel legumeModel;
    private int number;

    public LegumeInventoryItem(LegumeModel legumeModel) {
        this.legumeModel = legumeModel;
        this.number = 0;
    }

    public TypeLegume getTypeLegume() {
        return legumeModel.getType();
    }

    public int getNumber() {
        return number;
    }

    public void incrementNumber() {
        this.number++;
    }
}
