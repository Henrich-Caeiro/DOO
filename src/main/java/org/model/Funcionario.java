package org.model;

import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Pessoa {
    private int id;
    private Funcao funcao;
    private ArrayList<String> diasTrabalho;
    private ArrayList<String> cargaTrabalho;

    public Funcionario(String nome, String sexo, int idade, String cpf, String telefone, ArrayList<String> diasTrabalho, ArrayList<String> cargaTrabalho) {
        super(nome, sexo, idade, cpf, telefone);
        this.id = -1; // Inicializa o ID como -1 (ou outro valor padrão)
        this.diasTrabalho = new ArrayList<>();
        this.cargaTrabalho = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public ArrayList<String> getDiasTrabalho() {
        return diasTrabalho;
    }

    public void setDiasTrabalho(ArrayList<String> diasTrabalho) {
        this.diasTrabalho = diasTrabalho;
    }

    public ArrayList<String> getCargaTrabalho() {
        return cargaTrabalho;
    }

    public void setCargaTrabalho(ArrayList<String> cargaTrabalho) {
        this.cargaTrabalho = cargaTrabalho;
    }
    public void modificaFuncao(Funcao novaFuncao) {
        this.funcao = novaFuncao;
    }

    public void modificaDiasTrabalho(ArrayList<String> novosDiasTrabalho) {
        this.diasTrabalho = new ArrayList<>(novosDiasTrabalho);
    }

    public void modificaCarga(ArrayList<String> novaCargaTrabalho) {
        this.cargaTrabalho = novaCargaTrabalho;
    }

    // Métodos Externos
    public void cadastraCliente(Cliente cliente, Pet pet) {
        cliente.cadastrarPet(pet);
    }

    public void adicionaProduto(Produto produto, Servico servico) {
        servico.adicionarProduto(produto, 1);
    }

    public void finalizaServico(Servico servico) {
        servico.finalizarServico();
    }

    public void removeProduto(Servico servico, Produto produto) {
        servico.removerProduto(produto, 1);
    }

    public float getValorTotal(Servico servico) {
        return servico.getValorTotal();
    }

    // Métodos de Gerenciamento para um Gerente
    public void gerenciarModificacaoFuncao(Funcionario funcionario, Funcao novaFuncao) {
        if (Funcao.GERENTE == this.funcao) {
            funcionario.modificaFuncao(novaFuncao);
        } else {
            throw new UnsupportedOperationException("Somente gerentes podem modificar a função de outros funcionários.");
        }
    }

    public void gerenciarModificacaoDiasTrabalho(Funcionario funcionario, ArrayList<String> novosDiasTrabalho) {
        if (Funcao.GERENTE == this.funcao) {
            funcionario.modificaDiasTrabalho(novosDiasTrabalho);
        } else {
            throw new UnsupportedOperationException("Somente gerentes podem modificar os dias de trabalho de outros funcionários.");
        }
    }

    public void gerenciarModificacaoCarga(Funcionario funcionario, ArrayList<String> novaCargaTrabalho) {
        if (Funcao.GERENTE == this.funcao) {
            funcionario.modificaCarga(novaCargaTrabalho);
        } else {
            throw new UnsupportedOperationException("Somente gerentes podem modificar a carga de trabalho de outros funcionários.");
        }
    }

    // Métodos para Gerenciamento de Estoque
    public void adicionaItemEstoque(Estoque estoque, String nome, int quantidade, float preco) {
        estoque.adicionarProduto(nome, preco, quantidade);
    }

    public void removeItemEstoque(Estoque estoque, String nome, int quantidade) {
        estoque.removerItem(nome, quantidade);
    }

    public String pegarNomeItemEstoque(Estoque estoque,String nome) {
        return estoque.pegarNomeProduto(nome);
    }
}
