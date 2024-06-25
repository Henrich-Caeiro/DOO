package org.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controller.ControllerLoader;

import java.io.IOException;

public class ControllerUtil {
    private Parent root;
    private Object controller;

    //Utilize este changeScene para quando não precisar de pegar um controller antes de mostrar a tela
    public void changeScene(FXMLLoader loader, ActionEvent event, String windowTitle) throws IOException {
        root = loader.load();
        showScene(event, windowTitle);
    }

    //Utilize este changeScene para quando precisar de pegar um controller antes de mostrar a tela
    public void changeScene(ActionEvent event, String windowTitle) throws IOException {
        showScene(event, windowTitle);
    }

    private void showScene(ActionEvent event, String windowTitle) throws IOException {
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(windowTitle);
        stage.setScene(scene);
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    //APPLICATION_MODAL define que apenas a nova tela será interagivel no app inteiro enquanto estiver aberta, portanto
    //apenas deixe isAppModel sendo true em avisos e/ou telas de confirmacao.
    public void openWindow(String title, boolean isAppModal) throws IOException {
//        Pane graph = loader.load();
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);

        if (isAppModal) stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();
    }

    public FXMLLoader generateLoader(String packageName, String fxmlName) throws IOException {
        ControllerLoader controllerLoader = new ControllerLoader();
        return controllerLoader.generateLoader(packageName, fxmlName);
    }

    //Use este metodo quando precisar pegar o controller, ao usar ele, o root da cena será criado e o controller
    //será setado.
    public void load(FXMLLoader loader) throws IOException {
        root = loader.load();
        controller = loader.getController();
    }

    //Após usar o metodo load() pegue o controller com este metodo. Não esqueça de fazer casting ex:
    //Controller controller = (Controller) controllerUtil.getController();
    public Object getController() {
        return controller;
    }
}