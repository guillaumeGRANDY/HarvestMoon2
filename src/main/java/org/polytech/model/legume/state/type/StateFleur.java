package org.polytech.model.legume.state.type;

import org.polytech.model.legume.state.StateType;

import java.io.Serializable;
import java.util.Map;

public class StateFleur extends State implements Serializable {
    /**
     * Constructeur de l'état
     */
    public StateFleur(Map<StateType, Long> seuilCroissances) {
        super(seuilCroissances.get(StateType.FLEURIE));
        this.croissances=seuilCroissances;
    }

    @Override
    public State nextState() {
        return new StateMature(croissances);
    }

    @Override
    public StateType stateType() {
        return StateType.FLEURIE;
    }
}
