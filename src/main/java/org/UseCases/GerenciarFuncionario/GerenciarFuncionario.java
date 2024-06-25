package org.UseCases.GerenciarFuncionario;

import org.DAO.Funcionario.FuncionarioDAO;
import org.model.Funcionario;

import java.util.ArrayList;

public class GerenciarFuncionario {
    Funcionario funcionario;
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public ArrayList<Funcionario> getAllFuncionarios(){
        return funcionarioDAO.getAllFuncionarios();
    }

    public Funcionario getFuncionarioByCPF(String cpf){
        return funcionarioDAO.getFuncionarioByCPF(cpf);
    }
}
