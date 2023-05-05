package model;

import java.util.Observable;
import java.util.Random;

public class Ground2 extends Observable {
    private final Case[][] plants;
    private final int rows;

    public int getRows() {
        return rows;
    }

    private final int cols;

    public int getCols() {
        return cols;
    }

    private final int timesUntilUpdate = 1000;

    public Ground2(int rows, int cols) {
        plants = new Case[rows][cols];
        this.rows = rows;
        this.cols = cols;
        changePlants();
    }

    public void initGame() {
        initPlants();
        setChanged();
        notifyObservers(this.plants);
    }

    public Case[][] getPlants() {
        return plants;
    }

    public Case getCase(int row, int col) {
        return this.plants[row][col];
    }

    /**
     * Place une plante ou enlève une plante d'une case
     *
     * @param value  true => met une plante, false => enlève une plante
     * @param numRow numéro de ligne
     * @param numCol numéro de colonne
     */
    public void setPlant(Case value, int numRow, int numCol) {
        this.plants[numRow][numCol] = value;
    }

    /**
     * Change les plantes à intervale de temps régulier: timesUntilUpdate
     */
    public void changePlants() {
        // run in a timesUntilUpdate
        Runnable runnable = () -> {
            while (true) {
                initPlants();
                setChanged();
                notifyObservers(this.plants);
                try {
                    Thread.sleep(timesUntilUpdate);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void initPlants() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.plants[row][col] = new Case(new Random().nextBoolean());
            }
        }
    }
}
