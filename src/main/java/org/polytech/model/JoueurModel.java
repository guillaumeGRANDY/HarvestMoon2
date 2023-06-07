package org.polytech.model;

import org.polytech.model.inventory.Inventory;
import org.polytech.model.inventory.LegumeInventoryItem;
import org.polytech.model.legume.LegumeModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class JoueurModel extends Observable implements Serializable {
    /**
     * Argent que le joueur possède
     */
    private double solde;

    public double getSolde() {
        return solde;
    }

    private final Inventory inventory;

    public JoueurModel(double solde) {
        this.solde = solde;
        this.inventory = new Inventory();
        this.setChanged();
        this.notifyObservers();
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    private boolean canBuy(double price) {
        return this.solde - price >= 0;
    }

    /**
     * Achète quelque chose et décrèmente le solde si c'est possible d'acheter
     * @param price le prix de l'objet à acheter
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
     */
    public void buy(LegumeModel legume) throws Exception {
        this.buy(legume.getPrice());
    }

    public void addToInventory(LegumeModel legumeModel) {
        this.inventory.addLegume(legumeModel);
    }

    public void sell(LegumeInventoryItem legumeInventoryItem) {
        ArrayList<LegumeModel> legumesToSell = legumeInventoryItem.getLegumesOfTypeLegume();
        for (LegumeModel legume : legumesToSell) {
            double price = PrixMarche.getInstance().getPrice(legume.getType());
            this.solde += price;
        }
        legumeInventoryItem.deleteAllLegumes();
        this.setChanged();
        this.notifyObservers();
    }
}
