package org.UseCases.GerenciarFuncionario;

import org.DAO.Funcionario.FuncionarioDAO;
import org.model.Funcao;
import org.model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class CadastrarFuncionario {
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public String cadastrar(String nome, String sexo, int idade, String cpf, String telefone, Funcao funcao,
                            ArrayList<String> diasTrabalho, ArrayList<String> cargaTrabalho) {
        // Verifica se todos os campos são válidos
        final String validationCheck = isValid(nome, sexo, idade, cpf, telefone, funcao, diasTrabalho, cargaTrabalho);
        if (!validationCheck.isEmpty()) return validationCheck;

        // Lógica de negócio
        Funcionario funcionario = new Funcionario(nome, sexo, idade, cpf, telefone, diasTrabalho, cargaTrabalho);
        funcionario.setFuncao(funcao);
        funcionario.setDiasTrabalho(new ArrayList<>(diasTrabalho));
        funcionario.setCargaTrabalho(new ArrayList<>(cargaTrabalho));

        // Adicionar funcionário ao DAO
        funcionarioDAO.adicionarFuncionario(funcionario);

        return "Funcionário cadastrado com sucesso!";
    }

    private String isValid(String nome, String sexo, int idade, String cpf, String telefone, Funcao funcao,
                           List<String> diasTrabalho, List<String> cargaTrabalho) {
        if (nome.isEmpty() || sexo.isEmpty() || cpf.isEmpty() || telefone.isEmpty()) {
            return "Preencha todos os campos";
        }
        if (idade < 18) {
            return "Funcionário deve ter no mínimo 18 anos.";
        }
        if (!(sexo.equals("Masculino") || sexo.equals("Feminino") || sexo.equals("Outro"))) {
            return "Sexo inválido";
        }
        if (cpf.length() != 11) {
            return "CPF deve ter 11 dígitos.";
        }
        if (telefone.length() != 11) {
            return "Telefone deve ter 11 dígitos.";
        }
        if (funcao == null) {
            return "Selecione a função do funcionário";
        }
        if (diasTrabalho == null || diasTrabalho.isEmpty()) {
            return "Selecione os dias de trabalho do funcionário";
        }
        if (cargaTrabalho == null || cargaTrabalho.isEmpty()) {
            return "Defina a carga de trabalho do funcionário";
        }

        return "";
    }
}
