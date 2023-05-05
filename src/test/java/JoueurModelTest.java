package test.java;

import model.JoueurModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JoueurModelTest {
    @Test
    @DisplayName("Le joueur ne peux pas acheter lorsqu'il n'a pas assez d'argent")
    public void cantBuyWhenPlayerDontHaveEnoughSolde() {
        // Given
        double solde = 1000;
        JoueurModel joueurModel = JoueurModel.createDefault(solde);

        // When
        boolean hasBought = joueurModel.buy(2000);

        // Then
        Assertions.assertEquals(solde, joueurModel.getSolde());
        Assertions.assertFalse(hasBought);
    }

    @Test
    @DisplayName("Le joueur peux acheter lorsqu'il a assez d'argent")
    public void canBuyWhenPlayerHaveSolde() {
        // Given
        double solde = 1000;
        JoueurModel joueurModel = JoueurModel.createDefault(solde);

        // When
        boolean hasBought = joueurModel.buy(1000);

        // Then
        Assertions.assertEquals(0, joueurModel.getSolde());
        Assertions.assertTrue(hasBought);
    }
}