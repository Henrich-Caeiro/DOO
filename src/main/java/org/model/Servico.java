package org.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Servico {
    private int id;
    private Map<Produto, Integer> produtos;
    private float valorTotal;
    private Cliente cliente;
    private ArrayList<Funcionario> funcionarios;
    private LocalDate data;
    private boolean finalizado;

    public Servico(int id) {
        this.id = id;
        this.produtos = new HashMap<>();
        this.funcionarios = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        produtos.put(produto, produtos.getOrDefault(produto, 0) + quantidade);
        calculaTotal();
    }

    public void removerProduto(Produto produto, int quantidade) {
        if (produtos.containsKey(produto)) {
            int quantidadeAtual = produtos.get(produto);
            if (quantidade >= quantidadeAtual) {
                produtos.remove(produto);
            } else {
                produtos.put(produto, quantidadeAtual - quantidade);
            }
            calculaTotal();
        }
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
    }

    private void calculaTotal() {
        valorTotal = produtos.entrySet().stream()
                .map(entry -> entry.getKey().getValor() * entry.getValue())
                .reduce(0.0f, Float::sum);
    }

    public void finalizarServico() {
        finalizado = true;
    }

    public Map<Produto, Integer> getProdutos() {
        return produtos;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public LocalDate getData() {
        return data;
    }

    public boolean isFinalizado() {
        return finalizado;
    }
}
