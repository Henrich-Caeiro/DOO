package org.DAO.Funcionario;

import org.DAO.LoaderDAO;
import org.model.Cliente;
import org.model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    LoaderDAO loaderDAO = new LoaderDAO();
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private int proximoId = 1; // Variável para controlar o próximo ID a ser atribuído


    public ArrayList<Funcionario> getAllFuncionarios() {
        return funcionarios;
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionario.setId(proximoId); // Define o ID do funcionário
        funcionarios.add(funcionario);
        proximoId++;
    }

    public String atualizarFuncionario(Funcionario funcionarioEdited) {
        for (Funcionario func : funcionarios) {
            if (func.getCpf().equals(funcionarioEdited.getCpf())) {
                return updateThatFunc(funcionarioEdited, func);
            }
        }
        return "Funcionario não encontrado";
    }
public Funcionario getFuncionarioByCPF(String cpf){
    for (Funcionario funcionario : funcionarios) {
        if(funcionario.getCpf().equals(cpf)){
            return funcionario;
        }
    }
    return null;
    }
    private String updateThatFunc(Funcionario editedClient, Funcionario funcionario){
        funcionarios.remove(funcionario);
        funcionarios.add(editedClient);
        try{
            loaderDAO.writeEmployeeData(funcionarios);
        }catch (Exception e){
        return e.getMessage();
        }
        return "Alterado com sucesso.";
        }
    public Funcionario buscarFuncionario(int id) {
        // Implementar lógica para buscar o funcionário pelo ID
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    public List<Funcionario> listarFuncionarios() {
        return new ArrayList<>(funcionarios);
    }

    public void removerFuncionario(int id) {
        // Implementar lógica para remover o funcionário pelo ID
        funcionarios.removeIf(f -> f.getId() == id);
    }
}
