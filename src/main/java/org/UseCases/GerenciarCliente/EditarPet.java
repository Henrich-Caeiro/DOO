package org.UseCases.GerenciarCliente;

import org.DAO.Cliente.ClienteDAO;
import org.model.Cliente;
import org.model.Pet;
import org.model.Porte;
import org.model.Raca;

public class EditarPet {
    private ClienteDAO dao = new ClienteDAO();
    private Pet pet = new Pet("Teste", 2, "Fêmea", Raca.BULLDOG, Porte.MEDIO);
    private Cliente owner = new Cliente("John Doe", "Masculino", 26, "12345678900", "12345678910");

    public String editar(String newName, String newAge, String newGender, Porte newSize){
        if(newName.isEmpty() || newGender.isEmpty() || newAge.isEmpty() || newSize == null) return "Nenhum campo deve estar vazio.";
        if(!(newGender.equals("Macho") || newGender.equals("Fêmea"))){
            return "Sexo inválido";
        }

        int age = Integer.parseInt(newAge);

        Pet editedPet = new Pet(newName, age, newGender, pet.getRaca(), newSize);
        return dao.alterarPet(editedPet, owner, pet.getNome());
    }

    public String addGuardian(String newGuardianName){
        if(newGuardianName.isEmpty()) return "O nome do responsável não pode estar vazio.";
        return dao.addGuardian(pet, owner, newGuardianName);
    }

    public String removeGuardian(String guardianName){
        if(guardianName.isEmpty()) return "O nome do responsável não pode estar vazio.";
        return dao.removeGuardian(pet, owner, guardianName);
    }

    public String addObservacao(String title, String observacao){
        if(title.isEmpty() || observacao.isEmpty()) return "Nenhum campo deve estar vazio.";
        owner.adicionaObservacaoPet(title, observacao, pet);
        return dao.alterarPet(pet, owner, pet.getNome());
    }

    public void setPetAndOwner(Pet pet, Cliente owner){
        this.pet = pet;
        this.owner = owner;
    }
}
