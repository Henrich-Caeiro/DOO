package org.controller.Cliente;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.UseCases.GerenciarCliente.GerenciarCliente;
import org.Utils.ControllerUtil;
import org.model.Cliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GerenciarClientesController {
    @FXML
    private Button btnBackwards;

    @FXML
    private Button btnDetails;

    @FXML
    private Button btnRegister;

    @FXML
    private TableColumn<?,?> columnCpf;

    @FXML
    private TableColumn<?,?> columnName;

    @FXML
    private TableColumn<?,?> columnPhone;

    @FXML
    private TableView<Cliente> tableClients;

    private final ObservableList<Cliente> clientsList = FXCollections.observableArrayList();

    private final GerenciarCliente gerenciarCliente = new GerenciarCliente();
    private final ControllerUtil controllerUtil = new ControllerUtil();

    @FXML
    public void initialize() {
        ArrayList<Cliente> clients = gerenciarCliente.getAllClients();

        clientsList.clear();
        clientsList.addAll(clients);

        fillTable();
    }

    @FXML
    public void details(ActionEvent event) throws IOException {
        FXMLLoader loader = controllerUtil.generateLoader("Cliente", "visualizar_cliente.fxml");
        controllerUtil.load(loader);
        VisualizarClienteController controller = (VisualizarClienteController) controllerUtil.getController();
        controller.setClient(gerenciarCliente.getClientFromCpf(tableClients
                .getSelectionModel()
                .getSelectedItem()
                .getCpf()));
        controllerUtil.changeScene(event, "Visualização do Cliente");
    }

    @FXML
    public void register(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adicionar_cliente.fxml"));
        controllerUtil.changeScene(loader, event, "Registro de Cliente");
    }

    @FXML
    public void backwards(ActionEvent event) throws IOException {
        FXMLLoader loader = controllerUtil.generateLoader("Menu", "HomeScreen.fxml");
        controllerUtil.changeScene(loader, event, "Menu Principal");
    }

    private void fillTable(){
        columnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableClients.setItems(clientsList);
    }
}
