package model;

import java.util.Observable;

public class CaseModel extends Observable {
    private boolean isPlanted = false;

    public boolean isPlanted() {
        return isPlanted;
    }

    public void changePlanted() {
        this.isPlanted = !isPlanted;
        this.setChanged();
        this.notifyObservers(this.isPlanted);
    }

    public CaseModel(boolean isPlanted) {
        this.isPlanted = isPlanted;
    }

    public void NotiAll() {
        this.setChanged();
        this.notifyObservers(this.isPlanted);
    }
}
