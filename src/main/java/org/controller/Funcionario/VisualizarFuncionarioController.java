package org.controller.Funcionario;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import org.DAO.Funcionario.FuncionarioDAO;
import org.UseCases.GerenciarFuncionario.EditarFuncionario;
import org.Utils.ControllerUtil;
import org.controller.Popups.WarningController;
import org.model.Funcionario;
import org.model.Funcao;

import java.io.IOException;

public class VisualizarFuncionarioController {
    FuncionarioDAO funcionarioDAO;
    @FXML
    private Button btnEditar;

    @FXML
    private Button btnIniciaEdicao;

    @FXML
    private Button btnVoltar;

    @FXML
    private ChoiceBox<String> choiceSexo;

    @FXML
    private ChoiceBox<String> choiceFuncao;

    @FXML
    private TextField txtfNome;

    @FXML
    private TextField txtfCpf;

    @FXML
    private TextField txtfIdade;

    @FXML
    private TextField txtfTelefone;

    ControllerUtil controllerUtil = new ControllerUtil();
    Funcionario funcionario;

    @FXML
    public void initialize() {
        // Configurar opções de sexo
        choiceSexo.setItems(FXCollections.observableArrayList("Masculino", "Feminino", "Outro"));

        // Configurar opções de função
        choiceFuncao.setItems(FXCollections.observableArrayList("GERENTE", "OPERADOR", "SUPERVISOR")); // Ajustar conforme as funções disponíveis
    }

    @FXML
    void edit(ActionEvent event) {
        EditarFuncionario editor = new EditarFuncionario(funcionarioDAO);
        String result = editor.editarFuncionario(
                funcionario.getId(),
                txtfNome.getText(),
                txtfCpf.getText(),
                txtfIdade.getText(),
                choiceSexo.getValue(),
                txtfTelefone.getText(),
                choiceFuncao.getValue(),
                funcionario.getDiasTrabalho(),
                funcionario.getCargaTrabalho()
        );
        if(!result.equals("Funcionário alterado com sucesso")){
            setDefaultValues();
        }
        System.out.println(result);
        btnIniciaEdicao.setText("Iniciar Edição");
        changeEditableStatus(false);
        showPopup(result);
    }

    @FXML
    void backwards(ActionEvent event) throws IOException {
        FXMLLoader loader = controllerUtil.generateLoader("Funcionario", "gerenciar_funcionarios.fxml.fxml");
        controllerUtil.changeScene(loader, event, "Gerenciar Funcionários");
    }

    @FXML
    void handleEditionButton(ActionEvent event) {
        if(btnIniciaEdicao.getText().equals("Iniciar Edição")) {
            btnIniciaEdicao.setText("Cancelar Edição");
            changeEditableStatus(true);
        } else {
            btnIniciaEdicao.setText("Iniciar Edição");
            changeEditableStatus(false);
            setDefaultValues();
        }
    }

    private void changeEditableStatus(boolean status){
        btnEditar.setDisable(!status);
        txtfNome.setEditable(status);
        txtfCpf.setEditable(status);
        txtfIdade.setEditable(status);
        txtfTelefone.setEditable(status);
        choiceSexo.setDisable(!status);
        choiceFuncao.setDisable(!status);
    }

    private void setDefaultValues(){
        txtfNome.setText(funcionario.getNome());
        txtfCpf.setText(funcionario.getCpf());
        txtfIdade.setText(Integer.toString(funcionario.getIdade()));
        txtfTelefone.setText(funcionario.getTelefone());
        choiceSexo.setValue(funcionario.getSexo());
        choiceFuncao.setValue(funcionario.getFuncao().toString());
    }

    public void setFuncionario(Funcionario funcionario){
        this.funcionario = funcionario;
        setDefaultValues();
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
