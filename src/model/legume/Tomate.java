package model.legume;

import model.legume.state.*;

public class Tomate extends LegumeModel {
    public Tomate() {
        super();
        this.stateMachine = new StateMachine() {

            public State graine() {
                return new State(10_000) {
                    public State nextState() {
                        return bourgon();
                    }

                    public StateType stateType() {
                        return StateType.GRAINE;
                    }
                };
            }

            public State bourgon() {
                return new State(5000) {
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
                return new State(10_000) {
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
                return new State(0) {
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
        return TypeLegume.TOMATE;
    }

    @Override
    public double getPrice() {
        return 100;
    }
}
