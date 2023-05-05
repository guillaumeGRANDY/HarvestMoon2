package model.legume;

import model.legume.state.State;
import model.legume.state.StateMachine;
import model.legume.state.StateType;

import java.util.Observable;

public abstract class LegumeModel extends Observable implements Runnable {
    protected StateMachine stateMachine;

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
}
