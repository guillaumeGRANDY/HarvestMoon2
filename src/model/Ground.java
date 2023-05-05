package model;

import java.util.Observable;
import java.util.Random;

public class Ground extends Observable {
    private final boolean[][] plants;
    private final int rows;

    public int getRows() {
        return rows;
    }

    private int cols;

    public int getCols() {
        return cols;
    }

    private int timesUntilUpdate = 1000;

    public Ground(int rows, int cols) {
        plants = new boolean[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    public boolean[][] getPlants() {
        return plants;
    }

    /**
     * Place une plante ou enlève une plante d'une case
     *
     * @param value  true => met une plante, false => enlève une plante
     * @param numRow numéro de ligne
     * @param numCol numéro de colonne
     */
    public void setPlant(boolean value, int numRow, int numCol) {
        this.plants[numRow][numCol] = value;
    }

    public void initGame() {
        initPlants();
        setChanged();
        notifyObservers(this.plants);
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

    /**
     * Choisi un booléen aléatoire pour chaque case du tableau 2d de la classe
     */
    public void initPlants() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.plants[row][col] = new Random().nextBoolean();
            }
        }
    }

    public void modifiePixel(int i, int j)
    {
        this.plants[i][j]=!this.plants[i][j];
        setChanged();
        notifyObservers(this.plants);
    }
}
