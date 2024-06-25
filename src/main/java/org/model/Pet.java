package org.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pet {
    private int id;
    private String nome;
    private int idade;
    private String sexo;
    private Raca raca;
    private Porte porte;
    private ArrayList<String> responsaveis;
    private Map<String, String> observacoes;

    public Pet(String nome, int idade, String sexo, Raca raca, Porte porte) {
        this.nome = nome;
        this.idade = idade;
        this.raca = raca;
        this.porte = porte;
        responsaveis = new ArrayList<>();
        observacoes = new HashMap<>();
    }

    public Pet(int id) {
        this.id = id;
    }

    public String checkObservacao(String key, String value) {
        String observation = observacoes.get(key);
        if (observation.equals(value)) {
            return observation;
        }
        return "";
    }

    public String getObservacao(String key) {
        return observacoes.get(key);
    }

    public void addObservacao(String key, String value) {
        observacoes.put(key, value);
    }

    public void removeObservacao(String key) {
        observacoes.remove(key);
    }

    public String findObservacao(String value) {
        for (String key : observacoes.keySet()) {
            if (observacoes.get(key).equals(value)) {
                return key;
            }
        }
        return "";
    }

    public boolean hasResponsavel(String name){
        return responsaveis.contains(name);
    }

    public void addResponsavel(String guardian) {
        if(hasResponsavel(guardian)) {
            responsaveis.add(guardian);
        }
    }

    public void removeResponsavel(String guardian) {
        responsaveis.remove(guardian);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Raca getRaca() {
        return raca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    public ArrayList<String> getResponsaveis() {
        return responsaveis;
    }

    public Map<String, String> getObservacoes() {
        return observacoes;
    }

    public String getSexo() {
        return sexo;
    }

//    public void setObservacoes(String observacoes) {
//        this.observacoes = observacoes;
//    }
}
