package org.polytech.model.legume;

public class LegumeFabrique {
    public static LegumeModel createLegume(TypeLegume typeLegume) {
        LegumeModel legumeModel = null;
        switch (typeLegume) {
            case TOMATE -> legumeModel = new Tomate();
            default -> throw new UnsupportedOperationException("Le type de légume que vous voulez créer " + typeLegume.getName() + " n'existe pas");
        }
        return legumeModel;
    }
}
