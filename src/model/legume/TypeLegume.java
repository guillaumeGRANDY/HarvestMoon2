package model.legume;

public enum TypeLegume {
    TOMATE("Tomate");

    private final String name;

    TypeLegume(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
