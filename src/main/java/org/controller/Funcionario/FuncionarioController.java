package org.controller.Funcionario;

import org.DAO.Funcionario.FuncionarioDAO;
import org.model.Funcionario;

import java.util.List;

public class FuncionarioController {
    private FuncionarioDAO funcionarioDAO;

    public FuncionarioController(FuncionarioDAO funcionarioDAO) {
        this.funcionarioDAO = funcionarioDAO;
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarioDAO.adicionarFuncionario(funcionario);
    }

    public void atualizarFuncionario(Funcionario funcionario) {
        funcionarioDAO.atualizarFuncionario(funcionario);
    }

    public Funcionario buscarFuncionario(int id) {
        return funcionarioDAO.buscarFuncionario(id);
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarioDAO.listarFuncionarios();
    }

    public void removerFuncionario(int id) {
        funcionarioDAO.removerFuncionario(id);
    }

}
