package org.model;

import java.util.ArrayList;

public class Cliente extends Pessoa {
    private ArrayList<Pet> pets = new ArrayList<Pet>();

    public Cliente(String nome, String sexo, int idade, String cpf, String telefone) {
        super(nome, sexo, idade, cpf, telefone);
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public void cadastrarPet(Pet pet){
        pets.add(pet);
    }

    public void removerPet(Pet pet){
        pets.remove(pet);
    }

    public void adicionaObservacaoPet(String key, String observation, Pet pet){
        pet.addObservacao(key, observation);
    }

    public void adicionaResponsavelPet(String guardian, Pet pet){
        pet.addResponsavel(guardian);
    }
}
