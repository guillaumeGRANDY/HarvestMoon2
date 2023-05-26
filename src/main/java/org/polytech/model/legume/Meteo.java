package org.polytech.model.legume;

import java.util.Random;
import java.util.Observable;

public class Meteo extends Observable implements Runnable{

    int pluit=5,soleil=5;
    Random random = new Random();

    @Override
    public void run() {
        int value=random.nextInt(3)-1;
        this.soleil+=value;
        this.soleil=Math.max(Math.min(this.soleil,9),0);
        value=random.nextInt(3)-1;
        this.pluit+=value;
        this.pluit=Math.max(Math.min(this.pluit,9),0);
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
