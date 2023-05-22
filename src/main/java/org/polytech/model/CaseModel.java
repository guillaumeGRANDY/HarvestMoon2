package org.polytech.model;


import org.polytech.model.legume.LegumeModel;

import java.util.Observable;

public class CaseModel extends Observable implements Runnable {
    private LegumeModel legumeModel = null;

    /**
     * Savoir si la case a un légume planté
     * @return true si la case a un légume planté
     */
    public boolean isPlanted() {
        return this.legumeModel != null;
    }

    /**
     * Plante un légume dans la case
     *
     * @param legume légume à planter
     */
    public void plant(LegumeModel legume) {
        this.legumeModel = legume;
        this.setChanged();
        this.notifyObservers(this.isPlanted());
    }

    public void NotiAll() {
        this.setChanged();
        this.notifyObservers(this.isPlanted());
    }

    /**
     * Fait croitre le légume
     */
    @Override
    public void run() {
        if(isPlanted()) {
            legumeModel.croissance(10);
            setChanged();
            notifyObservers();
        }
    }
}
