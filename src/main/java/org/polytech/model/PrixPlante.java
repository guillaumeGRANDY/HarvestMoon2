package org.polytech.model;

import org.polytech.model.legume.type.TypeLegume;

import java.util.Random;

public class PrixPlante {
    private TypeLegume typeLegume;
    private int prixBase;
    private int prixCourant;
    private Random random = new Random();

    public PrixPlante(TypeLegume typeLegume, int prixBase) {
        this.typeLegume = typeLegume;
        this.prixBase = prixBase;
        this.prixCourant = prixBase;
    }

    public TypeLegume getTypeLegume() {
        return typeLegume;
    }

    public int getPrixBase() {
        return prixBase;
    }

    public int getPrixCourant() {
        return prixCourant;
    }

    public void setPrixCourant(int prixCourant) {
        this.prixCourant = prixCourant;
    }

    public void getNextPrice()
    {
        this.prixCourant+=(random.nextInt(3)-1)*3;
        this.prixCourant=Math.min(this.prixBase*2,Math.max(this.prixBase/2,this.prixCourant));
    }
}
