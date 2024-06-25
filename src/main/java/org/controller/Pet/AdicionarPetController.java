package org.controller.Pet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.UseCases.GerenciarCliente.EditarCliente;
import org.Utils.ControllerUtil;
import org.controller.Cliente.VisualizarClienteController;
import org.controller.Popups.WarningController;
import org.model.Cliente;
import org.model.Porte;
import org.model.Raca;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class AdicionarPetController {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCancel;

    @FXML
    private ChoiceBox<String> choiceGender;

    @FXML
    private ChoiceBox<String> choicePorte;

    @FXML
    private ChoiceBox<String> choiceRaca;

    @FXML
    private TextField txtIdade;

    @FXML
    private Text txtMessage;

    @FXML
    private TextField txtNome;

    private final ControllerUtil controllerUtil = new ControllerUtil();
    private Cliente owner = new Cliente("Johny Doe", "Masculino", 26, "12345678900", "12345678910");

    @FXML
    void initialize() {
        choiceGender.getItems().addAll("Macho", "Fêmea");
        choicePorte.getItems().addAll(Arrays.stream(Porte.values())
                .map(Enum::toString)
                .toList());
        choiceRaca.getItems().addAll(Arrays.stream(Raca.values())
                .map(Enum::toString)
                .toList());
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = controllerUtil.generateLoader("Cliente", "visualizar_cliente.fxml");
        controllerUtil.load(loader);
        VisualizarClienteController controller = (VisualizarClienteController) controllerUtil.getController();
        controller.setClient(owner);
        controllerUtil.changeScene(event, "Visualizar Cliente");
    }

    @FXML
    void register(ActionEvent event) {
        EditarCliente editor = new EditarCliente();
        editor.setClient(owner);

        String result = editor.addPet(
                txtNome.getText(),
                txtIdade.getText(),
                choiceGender.getSelectionModel().getSelectedItem(),
                choiceRaca.getSelectionModel().getSelectedItem(),
                choicePorte.getSelectionModel().getSelectedItem());

        txtMessage.setText(result);
    }

    public void setOwner(Cliente owner) {
        this.owner = owner;
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
