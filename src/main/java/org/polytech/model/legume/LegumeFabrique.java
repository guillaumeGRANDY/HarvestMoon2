package org.polytech.model.legume;

import org.polytech.model.legume.type.*;

public class LegumeFabrique {
    public static LegumeModel createLegume(TypeLegume typeLegume) {
        LegumeModel legumeModel = null;
        switch (typeLegume) {
            case CACTUS -> legumeModel = new Cactus();
            case FLEUR_VIOLETTE -> legumeModel = new FleurViolette();
            case FLEUR_BLEU -> legumeModel = new FleurBleu();
            case CHAMPIGNON_MARRON -> legumeModel = new ChampignonMarron();
            case FLEUR_ORANGE -> legumeModel = new FleurOrange();
            case FLEUR_ROUGE -> legumeModel = new FleurRouge();
            case FLEUR_JAUNE -> legumeModel = new FleurJaune();
            case CHAMPIGNON_ROUGE -> legumeModel = new ChampignonRouge();
            default -> throw new UnsupportedOperationException("Le type de légume que vous voulez créer " + typeLegume.getName() + " n'existe pas");
        }
        return legumeModel;
    }
}
