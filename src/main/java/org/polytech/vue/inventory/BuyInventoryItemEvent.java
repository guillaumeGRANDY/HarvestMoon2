package org.polytech.vue.inventory;

import org.polytech.model.inventory.LegumeInventoryItem;

public interface BuyInventoryItemEvent {
    void handleEvent(LegumeInventoryItem legumeInventoryItem);
}
