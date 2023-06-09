package org.polytech.model;

import java.util.ArrayList;
import java.util.List;

public class Ordonnanceur {
    private final List<Runnable> runnables;
    private static Ordonnanceur instance;

    public Ordonnanceur() {
        this.runnables = new ArrayList<>();
    }

    public static Ordonnanceur getInstance() {
        if(instance == null) {
            instance = new Ordonnanceur();
        }
        return instance;
    }

    /**
     * Démarre l'ordonnanceur dans un thread séparé
     * @see Thread
     */
    public void start() {
        new Thread(() -> {
            try {
                actualize();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    /**
     * Actualise les runnables toutes les secondes
     * @throws InterruptedException si le thread est interrompu
     */
    private void actualize() throws InterruptedException {
        while (true) {
            if(UniteTemps.CONTINUE) {
                for (Runnable runnable : runnables) {
                    runnable.run();
                }
            }
            Thread.sleep(UniteTemps.ECHELLE_TEMPS);
        }
    }

    public void addRunnable(Runnable legumeModel) {
        this.runnables.add(legumeModel);
    }

}
