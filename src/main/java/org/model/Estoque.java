package org.model;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private final Map<String, Float> produtos; // Armazena nome e preço do produto
    private final Map<String, Integer> quantidadeNoEstoque; // Armazena nome e quantidade do produto

    public Estoque() {
        this.produtos = new HashMap<>();
        this.quantidadeNoEstoque = new HashMap<>();
    }

    // Adiciona um produto ao estoque com uma quantidade específica
    public void adicionarProduto(String nome, float preco, int quantidade) {
        produtos.put(nome, preco);
        quantidadeNoEstoque.put(nome, quantidadeNoEstoque.getOrDefault(nome, 0) + quantidade);
    }

    // Remove uma quantidade específica de um produto do estoque
    public void removerItem(String nome, int quantidade) {
        if (quantidadeNoEstoque.containsKey(nome)) {
            int quantidadeAtual = quantidadeNoEstoque.get(nome);
            if (quantidade >= quantidadeAtual) {
                quantidadeNoEstoque.remove(nome);
                produtos.remove(nome);
            } else {
                quantidadeNoEstoque.put(nome, quantidadeAtual - quantidade);
            }
        }
    }

    // Retorna o nome do produto (aqui apenas um exemplo, pois o nome já é a chave)
    public String pegarNomeProduto(String nome) {
        return nome;
    }

    // Reduz a quantidade de um produto no estoque
    public void reduzEstoque(String nome, int quantidade) {
        removerItem(nome, quantidade);
    }

    // Gera um alerta se a quantidade de um produto estiver abaixo de um determinado limite
    public void alertaBaixoEstoque(String nome, int limite) {
        if (quantidadeNoEstoque.getOrDefault(nome, 0) < limite) {
            System.out.println("Alerta: Estoque baixo para o produto " + nome);
        }
    }

    // Getters e Setters
    public Map<String, Float> getProdutos() {
        return produtos;
    }

    public Map<String, Integer> getQuantidadeNoEstoque() {
        return quantidadeNoEstoque;
    }

    public Integer getQtdProduto(String nome) {
        return quantidadeNoEstoque.get(nome);
    }

    public Float getPrecoProduto(String nome) {
        return produtos.get(nome);
    }
}
