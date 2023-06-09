package org.polytech.model.legume.state;

import org.polytech.model.legume.state.type.State;

import java.io.Serializable;

public class StateMachine implements Serializable {
    private State currentState;

    public StateMachine(State initialState) {
        this.currentState = initialState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public State currentState() {
        return currentState;
    }

    public void nextState() {
        if(currentState.getStadeCroissance() >= currentState().getSeuilCroissance()) {
            setCurrentState(currentState.nextState());
        }
    }
}
