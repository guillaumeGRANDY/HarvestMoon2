package org.polytech.model.legume.type;

public enum TypeLegume {
    CACTUS("cactus", "cactus"),
    FLEUR_VIOLETTE("fleur violette", "fleurViolette"),
    FLEUR_ORANGE("fleur orange", "fleurOrange"),
    FLEUR_BLEU("fleur bleu", "fleurBleu"),
    FLEUR_ROUGE("fleur rouge", "fleurRouge"),
    FLEUR_JAUNE("fleur jaune", "fleurJaune"),
    CHAMPIGNON_MARRON("champignon marron", "champignonMarron"),
    CHAMPIGNON_ROUGE("champignon rouge", "champignonRouge");

    private final String name;

    private final String imageName;

    TypeLegume(String name, String imageName) {
        this.name = name;
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public String getImageName() {
        return imageName;
    }
}
