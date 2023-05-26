import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.polytech.model.JoueurModel;

class JoueurModelTest {

    @Test
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