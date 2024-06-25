package org.controller.Pet;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.UseCases.GerenciarCliente.EditarPet;
import org.Utils.ControllerUtil;
import org.Utils.MapDataObject;
import org.controller.Cliente.VisualizarClienteController;
import org.controller.Popups.WarningController;
import org.model.Cliente;
import org.model.Pet;
import org.model.Porte;
import org.model.Raca;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class VisualizarPetController {
    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnIniciaEdicao;

    @FXML
    private Button btnRemover;

    @FXML
    private Button btnVoltar;

    @FXML
    private ChoiceBox<String> choiceSexo;

    @FXML
    private ListView<String> listResponsaveis;

    @FXML
    private TextField txtfDono;

    @FXML
    private TextField txtfIdade;

    @FXML
    private TextField txtfNome;

    @FXML
    private ChoiceBox<String> choiceRaca;

    @FXML
    private ChoiceBox<String> choicePorte;

    @FXML
    private TextField txtfResponsavel;

    @FXML
    private TextField txtfTitulo;

    @FXML
    private TextArea txtaDescricao;

    @FXML
    private Button btnAddObservacao;

    @FXML
    private Button btnRemoveObservacao;

    @FXML
    private TableView tableObservacoes;

    @FXML
    private TableColumn<String, String> columnObservacao;

    @FXML
    private TableColumn<String, String> columnTitulo;

    private final ControllerUtil controllerUtil = new ControllerUtil();
    private Pet pet = new Pet("Placeholder", 1, "Macho", Raca.GOLDEN_RETRIEVER, Porte.GRANDE);
    private Cliente owner = new Cliente("Johny Doe", "Masculino", 26, "12345678900", "12345678910");

    @FXML
    public void initialize() {
        choiceSexo.getItems().addAll("Macho", "Fêmea");
        choiceRaca.getItems().add(pet.getRaca().toString());
        choicePorte.getItems().addAll(Arrays.stream(Porte.values())
                .map(Porte::toString)
                .toList());
        setDefaultValues();
//        owner.cadastrarPet(pet);
    }

    @FXML
    void addResponsavel(ActionEvent event) {
        EditarPet editor = new EditarPet();
        editor.setPetAndOwner(pet, owner);
        String response = editor.addGuardian(txtfResponsavel.getText());
        if(!response.equals("Responsável adicionado.")){
            System.out.println(response);
            return;
        }
        listResponsaveis.getItems().add(txtfResponsavel.getText());
        setDefaultValues();
        txtfResponsavel.setText("");
        System.out.println(response);
    }

    @FXML
    void backwards(ActionEvent event) throws IOException {
        FXMLLoader loader = controllerUtil.generateLoader("Cliente", "visualizar_cliente.fxml");
        controllerUtil.load(loader);
        VisualizarClienteController controller = (VisualizarClienteController) controllerUtil.getController();
        controller.setClient(owner);
        controllerUtil.changeScene(event, "Visualizar Cliente");
    }

    @FXML
    void edit(ActionEvent event) {
        EditarPet editor = new EditarPet();
        editor.setPetAndOwner(pet, owner);
        String result = editor.editar(
                txtfNome.getText(),
                txtfIdade.getText(),
                choiceSexo.getValue(),
                pet.getPorte()
        );
        if(!result.equals("Pet alterado com sucesso")){
            setDefaultValues();
        }
        if(result.equals("Pet alterado com sucesso")){
            pet.setNome(txtfNome.getText());
            pet.setIdade(Integer.parseInt(txtfIdade.getText()));
        }
        btnIniciaEdicao.setText("Iniciar Edição");
        changeEditableStatus(false);
        showPopup(result);
    }

    @FXML
    void handleEditionButton(ActionEvent event) {
        if(btnIniciaEdicao.getText().equals("Iniciar Edição")) {
            btnIniciaEdicao.setText("Cancelar Edição");
            changeEditableStatus(true);
            return;
        }
        btnIniciaEdicao.setText("Iniciar Edição");
        changeEditableStatus(false);
        setDefaultValues();
    }

    @FXML
    void addObservacao(ActionEvent event) {
        owner.adicionaObservacaoPet(txtfTitulo.getText(), txtaDescricao.getText(), pet);
        EditarPet editor = new EditarPet();
        editor.setPetAndOwner(pet, owner);
        String message = editor.addObservacao(txtfTitulo.getText(), txtaDescricao.getText());
        fillTable();
        System.out.println(message);
        showPopup(message);
    }

    @FXML
    void removeObservacao(ActionEvent event){}

    private void changeEditableStatus(boolean status){
        btnEditar.setDisable(!status);
        txtfNome.setEditable(status);
        txtfIdade.setEditable(status);
        choiceSexo.setDisable(!status);
    }

    @FXML
    void removeResponsavel(ActionEvent event) {
        String guardian = listResponsaveis.getSelectionModel().getSelectedItem();
        EditarPet editor = new EditarPet();
        String response = editor.removeGuardian(guardian);
        if(response.equals("Responsável removido.")){
            System.out.println(response);
            return;
        }
        pet.removeResponsavel(guardian);
        listResponsaveis.getItems().remove(guardian);
        System.out.println(response);
        showPopup(response);
    }

    public void setPetAndOwner(Pet pet, Cliente owner) {
        this.pet = pet;
        this.owner = owner;
        setDefaultValues();
    }

    private void setDefaultValues(){
        txtfNome.setText(pet.getNome());
        txtfIdade.setText(String.valueOf(pet.getIdade()));
        txtfDono.setText(owner.getNome());

        choiceSexo.setValue(pet.getSexo());
        choiceRaca.setValue(pet.getRaca().toString());
        choicePorte.setValue(pet.getPorte().toString());

        fillTable();

        listResponsaveis.getItems().addAll(pet.getResponsaveis());
    }

    private void fillTable(){
        ObservableList<MapDataObject> observableList = FXCollections.observableArrayList();
        for(Map.Entry<String, String> entry : pet.getObservacoes().entrySet()){
            observableList.add(new MapDataObject(entry.getKey(), entry.getValue()));
        }
        columnTitulo.setCellValueFactory(new PropertyValueFactory<>("key"));
        columnObservacao.setCellValueFactory(new PropertyValueFactory<>("value"));
        tableObservacoes.setItems(observableList);
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
