package model;

import model.legume.LegumeModel;

public class JoueurModel {
    /**
     * Argent que le joueur possède
     */
    private double solde;

    private Inventory inventory;

    public Inventory getInventory() {
        return inventory;
    }

    public double getSolde() {
        return solde;
    }

    private static JoueurModel instance = null;

    private JoueurModel(double solde) {
        this.solde = solde;
        this.inventory = new Inventory();
    }

    public static void createDefault(double solde) {
        if(instance == null)  {
            instance = new JoueurModel(solde);
        }
    }

    public static JoueurModel getInstance() {
        return instance;
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
    }

    /**
     * Achète un légume et décrèmente le solde si c'est possible d'acheter
     * Ajoute le légume à l'inventaire du joueur
     * @param legume le légume à acheter
     * @return true si le joueur a pu acheter le légume
     */
    public void buy(LegumeModel legume) throws Exception {
        this.buy(legume.getPrice());
        this.addLegumeToInventory(legume);
    }

    private void addLegumeToInventory(LegumeModel legume) {
        inventory.addLegume(legume);
    }
}
