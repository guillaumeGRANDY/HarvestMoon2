package org.polytech;

import org.polytech.model.JardinModel;
import org.polytech.vue.JardinVue;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JardinModel model = new JardinModel(10, 10);

            JardinVue vue = new JardinVue(model);
            model.initGame();
        });
    }
}