package org.polytech.model;

import org.polytech.model.legume.LegumeModel;
import org.polytech.model.legume.type.TypeLegume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public class Inventory extends Observable {
    private final HashMap<TypeLegume, Integer> legumesWithQuantity;
    private final List<LegumeModel> legumes = new ArrayList<>();

    public HashMap<TypeLegume, Integer> getLegumesTypeWithQuantity() {
        return legumesWithQuantity;
    }

    public List<LegumeModel> getLegumes() {
        return legumes;
    }

    public Inventory() {
        this.legumesWithQuantity = new HashMap<>();
    }

    public void addLegume(LegumeModel legume) {
        this.legumes.add(legume);
        this.legumesWithQuantity.putIfAbsent(legume.getType(), 0);
        this.legumesWithQuantity.put(legume.getType(), this.legumesWithQuantity.get(legume.getType()) + 1);
        this.setChanged();
        this.notifyObservers();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for(LegumeModel legume : this.legumes) {
            str.append(legume.toString());
            str.append(" ");
            str.append(legume.getCurrentState().stateType());
            str.append("\n");
        }
        return str.toString();
    }
}