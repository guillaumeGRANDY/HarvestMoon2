package org.polytech.model;


import org.polytech.model.exception.CannotHarvestException;
import org.polytech.model.legume.LegumeModel;
import org.polytech.model.legume.state.StateType;

import java.io.Serializable;
import java.util.Observable;

public class CaseModel extends Observable implements Runnable, Serializable {
    private LegumeModel legumeModel = null;

    private JardinModel parent;

    public CaseModel(JardinModel parent) {
        this.parent = parent;
    }

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
            legumeModel.croissance(10+parent.getMeteo().getPluit()*parent.getMeteo().getSoleil());
            setChanged();
            notifyObservers();
        }
    }

    public LegumeModel getLegumeModel() {
        return legumeModel;
    }

    public void setLegumeModel(LegumeModel legumeModel) {
        this.legumeModel = legumeModel;
    }

    /**
     * Essaie de récolter le légume sur la case
     * @return le légume à récolter
     * @throws CannotHarvestException si le légume de la case ne peut pas être récolté
     */
    public LegumeModel tryHarvest() throws CannotHarvestException {
        if(this.getLegumeModel().getCurrentState().stateType()!= StateType.POURRIE) {
            LegumeModel legumeToHarvest = this.legumeModel;
            if (!this.legumeModel.tryHarvest()) {
                throw new CannotHarvestException(this.legumeModel);
            }
            this.legumeModel = null;
            this.NotiAll();
            return legumeToHarvest;
        }
        this.legumeModel=null;
        return null;
    }
}
