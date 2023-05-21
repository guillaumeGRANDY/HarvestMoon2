package model;

import java.util.Observable;
import java.util.Random;

public class JardinModel extends Observable {
    private final CaseModel[][] plants;
    private final int rows;

    public int getRows() {
        return rows;
    }

    private final int cols;

    public int getCols() {
        return cols;
    }

    private final int timesUntilUpdate = 1000;

    public JardinModel(int rows, int cols) {
        plants = new CaseModel[rows][cols];
        this.rows = rows;
        this.cols = cols;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                plants[i][j] = new CaseModel();
            }
        }
        changePlants();
    }

    public void initGame() {
        setChanged();
        notifyObservers(this.plants);
    }

    public CaseModel[][] getPlants() {
        return plants;
    }

    public CaseModel getCase(int row, int col) {
        return this.plants[row][col];
    }

    /**
     * Place une plante ou enlève une plante d'une case
     *
     * @param value  true => met une plante, false => enlève une plante
     * @param numRow numéro de ligne
     * @param numCol numéro de colonne
     */
    public void setPlant(CaseModel value, int numRow, int numCol) {
        this.plants[numRow][numCol] = value;
    }

    /**
     * Change les plantes à intervale de temps régulier: timesUntilUpdate
     */
    public void changePlants() {
        // run in a timesUntilUpdate
        Runnable runnable = () -> {
            while (true) {
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
}
