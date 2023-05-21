package model;

import model.legume.LegumeModel;
import model.legume.state.StateType;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<LegumeModel> legumes;


    public List<LegumeModel> getLegumes() {
        return this.legumes;
    }

    public Inventory() {
        this.legumes = new ArrayList<>();
    }

    public void addLegume(LegumeModel legume) {
        this.legumes.add(legume);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for(LegumeModel legume : this.legumes) {
            str.append(legume.toString());
            str.append(" ");
            str.append(legume.getCurrentState().stateType());
            str.append("\n");
        }
        return str.toString();
    }

    public List<LegumeModel> grainsToPlant() {
        List<LegumeModel> legumesToPlant = new ArrayList<>();
        for(LegumeModel legume : this.legumes) {
            if(!legume.isPlanted() && legume.getCurrentState().stateType().equals(StateType.GRAINE)) {
                legumesToPlant.add(legume);
            }
        }
        return legumesToPlant;
    }
}
