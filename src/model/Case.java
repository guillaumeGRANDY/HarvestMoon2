package model;

import java.util.Observable;

public class Case extends Observable {
    private boolean isPlanted = false;

    public boolean isPlanted() {
        return isPlanted;
    }

    public void changePlanted() {
        this.isPlanted = !isPlanted;
        this.setChanged();
        this.notifyObservers(this.isPlanted);
    }

    public Case(boolean isPlanted) {
        this.isPlanted = isPlanted;
    }

    public void NotiAll() {
        this.setChanged();
        this.notifyObservers(this.isPlanted);
    }
}
