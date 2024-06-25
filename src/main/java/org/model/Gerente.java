package org.model;

import java.util.ArrayList;

public class Gerente extends Funcionario {

    public Gerente(int id, String nome, String sexo, int idade, String cpf, String telefone, ArrayList<String> diasTrabalho, ArrayList<String> cargaTrabalho) {
        super(nome, sexo, idade, cpf, telefone, diasTrabalho,cargaTrabalho);
        setId(id);
        setFuncao(Funcao.GERENTE);
    }

    public void gerenciarModificacaoFuncao(Funcionario funcionario, Funcao novaFuncao) {
        funcionario.modificaFuncao(novaFuncao);
    }

    public void gerenciarModificacaoDiasTrabalho(Funcionario funcionario, ArrayList<String> novosDiasTrabalho) {
        funcionario.modificaDiasTrabalho(novosDiasTrabalho);
    }

    public void gerenciarModificacaoCarga(Funcionario funcionario, ArrayList<String> novaCargaTrabalho) {
        funcionario.modificaCarga(novaCargaTrabalho);
    }
}
