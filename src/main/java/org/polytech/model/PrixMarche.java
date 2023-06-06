package org.polytech.model;

import org.polytech.model.legume.type.TypeLegume;
import org.polytech.vue.BottomMenuItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Random;

public class PrixMarche extends Observable implements Runnable {
    PrixPlante[] prixLegume;
    public PrixMarche() {
        prixLegume=new PrixPlante[8];

        prixLegume[0]=new PrixPlante(TypeLegume.CACTUS, 50);
        prixLegume[1]=new PrixPlante(TypeLegume.FLEUR_VIOLETTE,500);
        prixLegume[2]=new PrixPlante(TypeLegume.FLEUR_ORANGE,150);
        prixLegume[3]=new PrixPlante(TypeLegume.FLEUR_BLEU,90);
        prixLegume[4]=new PrixPlante(TypeLegume.FLEUR_ROUGE,750);
        prixLegume[5]=new PrixPlante(TypeLegume.FLEUR_JAUNE,1000);
        prixLegume[6]=new PrixPlante(TypeLegume.CHAMPIGNON_MARRON,250);
        prixLegume[7]=new PrixPlante(TypeLegume.CHAMPIGNON_ROUGE,300);
    }

    public int getPrice(TypeLegume typeLegume)
    {
        for(int i=0;i<8;i++)
        {
            if (this.prixLegume[i].getTypeLegume() == typeLegume)
            {
                return this.prixLegume[i].getPrixCourant();
            }
        }
        return -1;
    }

    @Override
    public void run() {
        for (int i=0;i<8;i++)
        {
            this.prixLegume[i].getNextPrice();
            this.setChanged();
            this.notifyObservers();
        }
    }
}
