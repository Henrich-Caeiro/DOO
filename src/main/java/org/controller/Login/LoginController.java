package org.controller.Login;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Node;


import java.util.Objects;

public class LoginController extends Application {

//    public static void main(String[] args) {
//        launch(args);
//    }
    @FXML
    private TextField txtfUsername;
    @FXML
    private PasswordField txtfPassword;
    @FXML
    private Button btnRegistrar; //register
    @FXML
    private Button btnEnviar; //submit


    @Override
    public void start(Stage stage) throws Exception {
        final Pane graph = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        final Scene scene = new Scene(graph, 800, 600);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
//                      EXEMPLO DE ACTION
//    public void register(ActionEvent actionEvent) {
//        FXMLLoader loader = controllerUtil.generateLoader("Cliente", "gerenciar_clientes.fxml");
//        controllerUtil.changeScene(loader, event, "Gerenciar Clientes");
//    }

    public void register(ActionEvent actionEvent) {
    }
//
//    public void submit(ActionEvent actionEvent) {
//
//    }
    @FXML
    private void submit(ActionEvent event) {
        String username = txtfUsername.getText();
        String password = txtfPassword.getText();

        // Aqui você pode adicionar a lógica de autenticação
        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro de Autenticação");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha todos os campos.");
            alert.showAndWait();
        } else {
            // Autenticação fictícia
            if (username.equals("admin") && password.equals("admin")) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Login Bem-Sucedido");
                alert.setHeaderText(null);
                alert.setContentText("Bem-vindo, " + username + "!");
                alert.showAndWait();
                //muda de tela após o login
//                try {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/MainView.fxml"));
//                    Parent root = loader.load();
//
//                    // Obtenha o palco atual
//                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//                    // Defina a nova cena
//                    Scene scene = new Scene(root);
//                    stage.setScene(scene);
//                    stage.show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erro de Autenticação");
                alert.setHeaderText(null);
                alert.setContentText("Usuário ou senha inválidos.");
                alert.showAndWait();
            }
        }
    }
}
