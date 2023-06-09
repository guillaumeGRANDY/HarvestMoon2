package org.polytech.serialization;

import org.polytech.model.JardinModel;
import org.polytech.model.JoueurModel;

import java.io.Serializable;

public class GameData implements Serializable {
    private final JardinModel jardinModel;
    private final JoueurModel joueurModel;

    public GameData(JardinModel jardinModel, JoueurModel joueurModel) {
        this.jardinModel = jardinModel;
        this.joueurModel = joueurModel;
    }

    public JardinModel getJardinModel() {
        return jardinModel;
    }

    public JoueurModel getJoueurModel() {
        return joueurModel;
    }
}