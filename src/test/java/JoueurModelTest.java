import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.polytech.model.JoueurModel;

class JoueurModelTest {

    @Test
    public void cantBuyWhenPlayerDontHaveEnoughSolde() throws Exception {
        // Given
        double solde = 1000;
        JoueurModel joueurModel =new JoueurModel(solde);

        // When
        joueurModel.buy(2000);

        // Then
        Assertions.assertEquals(solde, joueurModel.getSolde());
    }

    @Test
    public void canBuyWhenPlayerHaveSolde() throws Exception {
        // Given
        double solde = 1000;
        JoueurModel joueurModel = new JoueurModel(solde);

        // When
        joueurModel.buy(1000);

        // Then
        Assertions.assertEquals(0, joueurModel.getSolde());
    }
}