package org.polytech.model.legume.state.type;

import org.polytech.model.legume.state.StateType;

import java.io.Serializable;
import java.util.Map;

public class StateBourgon extends State implements Serializable {
    public StateBourgon(Map<StateType, Long> seuilCroissances) {
        super(seuilCroissances.get(StateType.BOURGON));
        this.croissances = seuilCroissances;
    }


    @Override
    public State nextState() {
        return new StateFleur(croissances);
    }

    @Override
    public StateType stateType() {
        return StateType.BOURGON;
    }
}
