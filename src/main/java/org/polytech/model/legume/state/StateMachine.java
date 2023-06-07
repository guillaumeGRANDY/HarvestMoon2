package org.polytech.model.legume.state;

public abstract class StateMachine {
    private State currentState;

    public StateMachine() {
        this.currentState = graine();
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

    public abstract State graine();
    public abstract State bourgon();
    public abstract State fleurie();
    public abstract State mature();
    public abstract State pourrie();
}
