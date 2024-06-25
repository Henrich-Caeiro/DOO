package org.controller.Cliente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.UseCases.GerenciarCliente.CadastrarCliente;
import org.Utils.ControllerUtil;
import org.controller.Popups.WarningController;

import java.io.IOException;
import java.util.Objects;

public class AdicionarClienteController {
    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCancel;

    @FXML
    private ChoiceBox<String> choiceGender;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtNome;

    @FXML
    private Text txtMessage;

    @FXML
    private TextField txtTelefone;

    ControllerUtil controllerUtil = new ControllerUtil();

    @FXML
    void initialize(){
        choiceGender.getItems().addAll("Masculino", "Feminino", "Outro");
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = controllerUtil.generateLoader("Cliente", "gerenciar_clientes.fxml");
        controllerUtil.changeScene(loader, event, "Gerenciar Clientes");
    }

    @FXML
    void register() {
        CadastrarCliente registration = new CadastrarCliente();

        int convertedAge;
        try{
            convertedAge = Integer.parseInt(txtIdade.getText());
        }catch(NumberFormatException e){
            txtMessage.setText("Idade não é um número!");
            showPopup("Idade não é um número!");
            return;
        }

        String result = registration.cadastrar(txtNome.getText(), choiceGender.getValue(),
                convertedAge, txtCPF.getText(), txtTelefone.getText());
        txtMessage.setText(result);
        showPopup(result);
    }

    private void showPopup(String message){
        try {
            FXMLLoader loader = controllerUtil.generateLoader("Popups", "warning_window.fxml");
            controllerUtil.load(loader);
            WarningController controller = (WarningController) controllerUtil.getController();
            controller.setText(message);
            controllerUtil.openWindow("Aviso", true);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
