package org.polytech.model;

import java.io.Serializable;
import java.util.Random;
import java.util.Observable;

public class Meteo extends Observable implements Runnable, Serializable {

    int pluit=5,soleil=10;

    int[] tableauSoleil={0,0,0,1,2,3,4,5,6,7,8,10,10,10,8,7,6,5,4,3,2,1,0,0};
    int heure=11;
    Random random = new Random();

    @Override
    public void run() {
        heure=(heure+1)%24;
        this.soleil=Math.max(Math.min(tableauSoleil[heure]+random.nextInt(3)-1,10),0);
        this.pluit+=random.nextInt(3)-1;
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

    public int getHeure() {
        return heure;
    }
}
