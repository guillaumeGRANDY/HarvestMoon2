package org.polytech.model.legume.state;


import org.polytech.model.UniteTemps;

public abstract class State {
    /**
     * Le temps restant avant la transition vers l'état suivant
     * Le temps est en milliseconde
     */
    private long tempsAvantTransition;

    public double getTempsAvantTransition() {
        return tempsAvantTransition;
    }

    /**
     * Constructeur de l'état
     * @param tempsAvantTransition le temps restant avant la transition vers l'état suivant en milliseconde
     */
    public State(long tempsAvantTransition) {
        this.tempsAvantTransition = tempsAvantTransition;
    }

    /**
     * Le prochain état vers lequel il faut aller
     * @return le prochain état vers lequel aller
     */
    public abstract State nextState();
    public abstract StateType stateType();

    /**
     * Décrémente le temps restant de 1000ms avant le passage au prochain état
     */
    public void decrementerTempsRestant() {
        this.tempsAvantTransition -= UniteTemps.ECHELLE_TEMPS;
    }
}
