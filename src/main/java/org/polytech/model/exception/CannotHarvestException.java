package org.polytech.model.exception;

import org.polytech.model.legume.LegumeModel;

public class CannotHarvestException extends Exception {
    public CannotHarvestException(LegumeModel legumeModel) {
        super("Vous ne pouvez pas récupérer ce légume, il est encore à l'état " + legumeModel.getCurrentState().stateType().name());
    }
}
