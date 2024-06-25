package org.controller.Menu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.Utils.ControllerUtil;

import java.util.Objects;

public class HomeScreenController extends Application {

    @FXML
    private Button btnGerenciarClientes;

    @FXML
    private Button btnGerenciarEstoque;

    @FXML
    private Button btnGerenciarFuncionarios;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVerServicos;

    ControllerUtil controllerUtil = new ControllerUtil();

    @Override
    public void start(Stage stage) throws Exception {
        final Pane graph = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeScreen.fxml")));
        final Scene scene = new Scene(graph, 800, 600);
        stage.setTitle("Cadastro de Cliente");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void gerenciarClientes(ActionEvent event) {
        try {
            FXMLLoader loader = controllerUtil.generateLoader("Cliente", "gerenciar_clientes.fxml");
            controllerUtil.changeScene(loader, event, "Gerenciar Clientes");
        }catch (Exception e) {
            e.getMessage();
        }
    }

    @FXML
    void gerenciarEstoque(ActionEvent event) {

    }

    @FXML
    void gerenciarFuncionarios(ActionEvent event) {
        try {
            FXMLLoader loader = controllerUtil.generateLoader("Funcionario", "gerenciar_funcionarios.fxml");
            controllerUtil.changeScene(loader, event, "Gerenciar Funcionarios");
        }catch (Exception e) {
            e.getMessage();
        }

    }

    @FXML
    void sair(ActionEvent event) {
        controllerUtil.closeWindow(event);
    }

    @FXML
    void verServicos(ActionEvent event) {

    }

}
