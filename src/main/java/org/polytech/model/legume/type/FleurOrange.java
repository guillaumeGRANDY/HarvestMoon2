package org.polytech.model.legume.type;

import org.polytech.model.legume.LegumeModel;
import org.polytech.model.legume.state.State;
import org.polytech.model.legume.state.StateMachine;
import org.polytech.model.legume.state.StateType;

public class FleurOrange extends LegumeModel {
    public FleurOrange() {
        super();
        this.stateMachine = new StateMachine() {
            public State graine() {
                return new State(250) {
                    public State nextState() {
                        return bourgon();
                    }

                    public StateType stateType() {
                        return StateType.GRAINE;
                    }
                };
            }

            public State bourgon() {
                return new State(250) {
                    @Override
                    public State nextState() {
                        return fleurie();
                    }

                    @Override
                    public StateType stateType() {
                        return StateType.BOURGON;
                    }
                };
            }

            public State fleurie() {
                return new State(250) {
                    @Override
                    public State nextState() {
                        return mature();
                    }

                    @Override
                    public StateType stateType() {
                        return StateType.FLEURIE;
                    }
                };
            }

            public State mature() {
                return new State(250) {
                    @Override
                    public State nextState() {
                        return mature();
                    }

                    @Override
                    public StateType stateType() {
                        return StateType.MATURE;
                    }
                };
            }
        };
    }

    @Override
    public TypeLegume getType() {
        return TypeLegume.FLEUR_ORANGE;
    }

    @Override
    public double getPrice() {
        return 90;
    }
}
