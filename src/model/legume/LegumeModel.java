package model.legume;

import model.legume.state.State;
import model.legume.state.StateMachine;
import model.legume.state.StateType;

import java.util.HashMap;
import java.util.Observable;

public abstract class LegumeModel extends Observable implements Runnable {
    protected StateMachine stateMachine;
    private boolean isPlanted;

    public boolean isPlanted() {
        return isPlanted;
    }

    public static HashMap<TypeLegume, Integer> quantities = new HashMap<>();

    public LegumeModel() {
        quantities.putIfAbsent(getType(), 0);
        quantities.put(getType(), quantities.get(getType()) + 1);
    }

    public abstract TypeLegume getType();

    public abstract double getPrice();

    public State getCurrentState() {
        return this.stateMachine.currentState();
    }

    /**
     * Gère le cycle de vie du légume (graine, bourgon, fleurie, mature)
     * Notifie les observateurs lors d'un changement d'état
     */
    public void run() {
        if(this.stateMachine.currentState().stateType().equals(StateType.GRAINE)) {
            setChanged();
            notifyObservers();
        }
        this.stateMachine.currentState().decrementerTempsRestant();
        State oldState = this.stateMachine.currentState();
        this.stateMachine.nextState();
        System.out.println(this.stateMachine.currentState().stateType());
        if (!this.stateMachine.currentState().equals(oldState)) {
            setChanged();
            notifyObservers();
        }
    }

    public void plant() {
        this.isPlanted = true;
    }
}
