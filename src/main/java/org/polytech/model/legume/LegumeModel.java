package org.polytech.model.legume;


import org.polytech.model.legume.state.StateMachine;
import org.polytech.model.legume.state.type.State;
import org.polytech.model.legume.state.StateType;
import org.polytech.model.legume.state.type.StateGraine;
import org.polytech.model.legume.type.TypeLegume;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;

public abstract class LegumeModel extends Observable implements Serializable {
    protected StateMachine stateMachine;

    private boolean isPlanted;

    public boolean isPlanted() {
        return isPlanted;
    }

    private static int nextId = 0;
    private int id = 0;

    public static HashMap<TypeLegume, Integer> quantities = new HashMap<>();

    public LegumeModel() {
        this.id = ++nextId;
        quantities.putIfAbsent(getType(), 0);
        quantities.put(getType(), quantities.get(getType()) + 1);
    }

    public abstract TypeLegume getType();

    public abstract double getPrice();

    public State getCurrentState() {
        return this.stateMachine.currentState();
    }

    /**
     * Fait croître le légume d'un certain nombre de score
     * @param score le score de croissance à ajouter au légume
     */
    public void croissance(long score) {
        if(this.stateMachine.currentState() != null) {
            if(this.stateMachine.currentState().stateType().equals(StateType.GRAINE)) {
                setChanged();
                notifyObservers();
            }
            this.stateMachine.currentState().incrementScore(score);
            State oldState = this.stateMachine.currentState();
            this.stateMachine.nextState();
            if(this.stateMachine.currentState() == null) return;
            if (isPlanted && !this.stateMachine.currentState().equals(oldState)) {
                setChanged();
                notifyObservers();
            }
        }
    }

    public void plant() {
        this.isPlanted = true;
    }

    public void unPlant() {
        this.isPlanted = false;
    }

    public boolean tryHarvest() {
        if(this.getCurrentState().stateType().equals(StateType.MATURE)) {
            this.unPlant();
            return true;
        }
        return false;
    }
}
