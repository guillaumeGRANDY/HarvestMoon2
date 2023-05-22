package org.polytech.model.legume.state;

public abstract class State {
    /**
     * Seuil de croissance à atteindre
     */
    private long seuilCroissance;

    public double getSeuilCroissance() {
        return seuilCroissance;
    }

    private long stadeCroissance = 0;

    public long getStadeCroissance() {
        return stadeCroissance;
    }

    /**
     * Constructeur de l'état
     * @param seuilCroissance score de croissance avant de transiter vers l'état suivant
     */
    public State(long seuilCroissance) {
        this.seuilCroissance = seuilCroissance;
    }

    /**
     * Le prochain état vers lequel il faut aller
     * @return le prochain état vers lequel aller
     */
    public abstract State nextState();
    public abstract StateType stateType();

    /**
     * Incrémente le score de croissance
     * @param score le score à décrémenter
     */
    public void incrementScore(long score) {
        this.stadeCroissance += score;
    }
}
