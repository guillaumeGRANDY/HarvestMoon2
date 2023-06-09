package org.polytech.model.legume.state.type;

import org.polytech.model.legume.state.StateType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class State implements Serializable {
    /**
     * Seuil de croissance à atteindre
     */
    private long seuilCroissance;

    protected Map<StateType, Long> croissances;

    public double getSeuilCroissance() {
        return seuilCroissance;
    }

    private long stadeCroissance = 0;

    public long getStadeCroissance() {
        return stadeCroissance;
    }

    /**
     * Constructeur de l'état
     */
    public State(HashMap<StateType, Long> seuilCroissances) {
        this.croissances = seuilCroissances;
    }

    public State(Map<StateType, Long> seuilCroissances, long seuilCroissance) {
        croissances = seuilCroissances;
        this.seuilCroissance = seuilCroissance;
    }

    public State(Map<StateType, Long> seuilCroissances) {
        this.croissances = seuilCroissances;
    }

    public State(long seuilCroissance) {
        this.seuilCroissance = seuilCroissance;
    }

    /**
     * Le prochain état vers lequel il faut aller
     * @return le prochain état vers lequel aller
     */
    public abstract State nextState();
    public abstract StateType stateType();

    /**
     * Incrémente le score de croissance
     * @param score le score à décrémenter
     */
    public void incrementScore(long score) {
        this.stadeCroissance += score;
    }
}
