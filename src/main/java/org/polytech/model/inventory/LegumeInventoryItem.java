package org.polytech.model.inventory;

import org.polytech.model.legume.LegumeModel;
import org.polytech.model.legume.type.TypeLegume;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;

public class LegumeInventoryItem extends Observable {
    private TypeLegume typeLegume;
    private ArrayList<LegumeModel> legumesOfTypeLegume;

    public ArrayList<LegumeModel> getLegumesOfTypeLegume() {
        return legumesOfTypeLegume;
    }

    public LegumeInventoryItem(TypeLegume typeLegume) {
        this.typeLegume = typeLegume;
        legumesOfTypeLegume = new ArrayList<>();
    }

    public TypeLegume getTypeLegume() {
        return typeLegume;
    }

    public int getNumber() {
        return this.legumesOfTypeLegume.size();
    }

    public void addNewLegume(LegumeModel legumeModel) {
        assert legumeModel.getType().equals(typeLegume);
        this.legumesOfTypeLegume.add(legumeModel);
        this.setChanged();
        this.notifyObservers(this.legumesOfTypeLegume.size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LegumeInventoryItem that = (LegumeInventoryItem) o;
        return typeLegume == that.typeLegume && legumesOfTypeLegume.equals(that.legumesOfTypeLegume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeLegume, legumesOfTypeLegume);
    }

    public void deleteAllLegumes() {
        this.legumesOfTypeLegume = new ArrayList<>();
        this.setChanged();
        this.notifyObservers(this.legumesOfTypeLegume.size());
    }

    public boolean canSellLegumes() {
        return this.legumesOfTypeLegume.size() > 0;
    }
}
