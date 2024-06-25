package org.controller;

import javafx.fxml.FXMLLoader;
import org.controller.Pet.VisualizarPetController;

public class ControllerLoader {
    public FXMLLoader generateLoader(String packageName, String fxmlName){
        return new FXMLLoader(getClass().getResource(packageName + "/" + fxmlName));
    }
}
