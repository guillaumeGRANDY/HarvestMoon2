package org.polytech.model.legume;

import java.util.Random;
import java.util.Observable;

public class Meteo extends Observable implements Runnable{

    int pluit,soleil;
    Random random = new Random();

    @Override
    public void run() {
        this.soleil=random.nextInt(11);
        this.pluit=random.nextInt(11);
        this.setChanged();
        this.notifyObservers();
    }

    public int getPluit() {
        return pluit;
    }

    public int getSoleil() {
        return soleil;
    }
}
