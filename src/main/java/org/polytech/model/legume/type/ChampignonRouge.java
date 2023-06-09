package org.polytech.model.legume.type;

import org.polytech.model.legume.LegumeModel;
import org.polytech.model.legume.state.type.State;
import org.polytech.model.legume.state.StateMachine;
import org.polytech.model.legume.state.StateType;
import org.polytech.model.legume.state.type.StateGraine;

import java.util.Map;

public class ChampignonRouge extends LegumeModel {
    public ChampignonRouge() {
        super();
        State initialState = new StateGraine(Map.of(StateType.GRAINE, 120L, StateType.BOURGON, 130L, StateType.FLEURIE, 120L, StateType.MATURE, 130L));
        this.stateMachine = new StateMachine(initialState);
    }

    @Override
    public TypeLegume getType() {
        return TypeLegume.CHAMPIGNON_ROUGE;
    }

    @Override
    public double getPrice() {
        return 100;
    }
}
