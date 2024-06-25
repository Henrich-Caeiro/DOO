package org.DAO.Cliente;

import org.model.Cliente;
import org.model.Pet;

import java.util.ArrayList;

public interface ClienteDAOInterface {
    public String cadastrar(Cliente client);
    public String alterar(Cliente client);
    public Cliente getClienteByCpf(String cpf);
    public ArrayList<Cliente> getAllClients();

    public String addPet(Pet newPet, Cliente owner);
    public String removePet(Cliente owner, String petName);
    public String alterarPet(Pet pet, Cliente owner, String petName);
    public String addGuardian(Pet pet, Cliente owner, String guardianName);
    public String removeGuardian(Pet pet, Cliente owner, String guardianName);
}
