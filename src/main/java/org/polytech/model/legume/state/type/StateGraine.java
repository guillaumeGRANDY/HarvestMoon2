package org.polytech.model.legume.state.type;

import org.polytech.model.legume.state.StateType;

import java.io.Serializable;
import java.util.Map;

public class StateGraine extends State implements Serializable {

    public StateGraine(Map<StateType, Long> seuilCroissances) {
        super(seuilCroissances.get(StateType.GRAINE));
        this.croissances=seuilCroissances;
    }

    @Override
    public State nextState() {
        return new StateBourgon(croissances);
    }

    @Override
    public StateType stateType() {
        return StateType.GRAINE;
    }
}
