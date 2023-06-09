package org.polytech.model.legume.state.type;

import org.polytech.model.legume.state.StateType;

import java.io.Serializable;
import java.util.Map;

public class StatePourrie extends State implements Serializable {
    /**
     * Constructeur de l'état
     *
     * @param seuilCroissance score de croissance avant de transiter vers l'état suivant
     */
    public StatePourrie(long seuilCroissance) {
        super(seuilCroissance);
    }

    public StatePourrie(Map<StateType, Long> seuilCroissances) {
        super(seuilCroissances.get(StateType.POURRIE));
        this.croissances=seuilCroissances;
    }

    @Override
    public State nextState() {
        return this;
    }

    @Override
    public StateType stateType() {
        return StateType.POURRIE;
    }
}
