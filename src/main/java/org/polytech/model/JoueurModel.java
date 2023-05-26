package org.polytech.model;

import org.polytech.model.legume.LegumeModel;

import java.util.Observable;

public class JoueurModel extends Observable {
    /**
     * Argent que le joueur possède
     */
    private double solde;

    public double getSolde() {
        return solde;
    }

    private Inventory inventory;

    public Inventory getInventory() {
        return inventory;
    }

    public JoueurModel(double solde) {
        this.solde = solde;
        this.inventory = new Inventory();
        this.setChanged();
        this.notifyObservers();
    }

    private boolean canBuy(double price) {
        return this.solde - price >= 0;
    }

    /**
     * Achète quelque chose et décrèmente le solde si c'est possible d'acheter
     * @param price le prix de l'objet à acheter
     * @return true si le joueur a pu acheter le légume
     */
    public void buy(double price) throws Exception {
        if(!this.canBuy(price)) throw new Exception("Le joueur n'a pas assez d'argent pour acheter cette graine !");
        this.solde -= price;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Achète un légume et décrèmente le solde si c'est possible d'acheter
     * Ajoute le légume à l'inventaire du joueur
     * @param legume le légume à acheter
     * @return true si le joueur a pu acheter le légume
     */
    public void buy(LegumeModel legume) throws Exception {
        System.out.println("buy");
        this.buy(legume.getPrice());
    }

    private void addLegumeToInventory(LegumeModel legume) {
        inventory.addLegume(legume);
    }
}
