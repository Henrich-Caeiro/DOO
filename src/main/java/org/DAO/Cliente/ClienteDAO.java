package org.DAO.Cliente;

import org.DAO.LoaderDAO;
import org.model.Cliente;
import org.model.Pet;

import java.util.ArrayList;

public class ClienteDAO implements ClienteDAOInterface {
    LoaderDAO loaderDAO = new LoaderDAO();
    ArrayList<Cliente> clients = loaderDAO.loadClientData();

    public String cadastrar(Cliente client){
        for(Cliente c : clients){
            if(c.getCpf().equals(client.getCpf())){
                return "CPF já cadastrado";
            }
        }
        clients.add(client);
        try{
            loaderDAO.writeClientsData(clients);
        }catch (Exception e){
            return e.getMessage();
        }
        return "Cliente cadastrado com sucesso";
    }

    public ArrayList<Cliente> getAllClients() {
        return clients;
    }

    public Cliente getClienteByCpf(String cpf){
        for (Cliente cliente : clients) {
            if(cliente.getCpf().equals(cpf)){
                return cliente;
            }
        }
        return null;
    }

    public String alterar(Cliente editedClient){
        for(Cliente client : clients){
            if(client.getCpf().equals(editedClient.getCpf())) {
                return changeTargetClient(editedClient, client);
            }
        }
        return "Cliente não encontrado";
    }

    private String changeTargetClient(Cliente editedClient, Cliente client){
        clients.remove(client);
        clients.add(editedClient);
        try{
            loaderDAO.writeClientsData(clients);
        }catch (Exception e){
            return e.getMessage();
        }
        return "Alterado com sucesso.";
    }

    public String addPet(Pet newPet, Cliente owner){
        for(Pet pet : owner.getPets()){
            if(pet.getNome().equals(newPet.getNome())){
                return "Pet já cadastrado";
            }
        }
        owner.cadastrarPet(newPet);
        return alterar(owner);
    }

    public String removePet(Cliente owner, String petName){
        for(Pet pet : owner.getPets()){
            if(pet.getNome().equals(petName)){
                owner.removerPet(pet);

                return alterar(owner);
            }
        }
        return "Pet não encontrado";
    }

    public String alterarPet(Pet editedPet, Cliente owner, String petName){
        for(Pet pet : owner.getPets()){
            if(pet.getNome().equals(petName)){
                owner.getPets().remove(pet);
                owner.getPets().add(editedPet);
                return alterar(owner);
            }
        }
        return "Pet não encontrado";
    }

    public String addGuardian(Pet pet, Cliente owner, String guardianName){
        for(String guardian : pet.getResponsaveis()){
            if(guardian.equals(guardianName)){
                return "Responsável ja na lista.";
            }
        }
        owner.adicionaResponsavelPet(guardianName, pet);
        return alterar(owner);
    }

    public String removeGuardian(Pet pet, Cliente owner, String guardianName){
        for(String guardian : pet.getResponsaveis()){
            if(guardian.equals(guardianName)){
                pet.removeResponsavel(guardianName);

                return alterarPet(pet, owner, pet.getNome());
            }
        }
        return "Responsável não encontrado.";
    }
}
