package model;

public class JoueurModel {
    /**
     * Argent que le joueur possède
     */
    private double solde;

    public double getSolde() {
        return solde;
    }

    private static final JoueurModel instance = null;

    private JoueurModel(double solde) {
        this.solde = solde;
    }

    public static JoueurModel createDefault(double solde) {
        if(instance == null)  {
            return new JoueurModel(solde);
        }
        return instance;
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
    public boolean buy(double price) {
        if(this.canBuy(price)) {
            this.solde -= price;
            return true;
        }
        return false;
    }
}
