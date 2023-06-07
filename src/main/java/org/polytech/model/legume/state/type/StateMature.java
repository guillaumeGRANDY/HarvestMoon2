package org.polytech.model.legume.state.type;

import org.polytech.model.legume.state.StateType;

import java.io.Serializable;
import java.util.Map;

public class StateMature extends State implements Serializable {
    /**
     * Constructeur de l'état
     *
     * @param seuilCroissance score de croissance avant de transiter vers l'état suivant
     */
    public StateMature(long seuilCroissance) {
        super(seuilCroissance);
    }

    public StateMature(Map<StateType, Long> seuilCroissances) {
        super(seuilCroissances.get(StateType.MATURE));
        this.croissances=seuilCroissances;
    }

    @Override
    public State nextState() {
        return new StatePourrie(croissances.get(stateType()));
    }

    @Override
    public StateType stateType() {
        return StateType.MATURE;
    }
}
