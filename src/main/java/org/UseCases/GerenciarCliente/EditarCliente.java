package org.UseCases.GerenciarCliente;

import org.DAO.Cliente.ClienteDAO;
import org.model.Cliente;
import org.model.Pet;
import org.model.Porte;
import org.model.Raca;

public class EditarCliente {
    ClienteDAO dao = new ClienteDAO();
    Cliente client = new Cliente("John Doe", "Masculino", 19, "12345678900", "12345678901");

    public String editar(String newName, String cpf, String newAge, String newGender, String newPhone){
        if(newName.isEmpty() || newPhone.isEmpty() || newGender.isEmpty() || newAge.isEmpty()) return "Nenhum campo deve estar vazio.";
        if(Integer.parseInt(newAge) < 18) return "O cliente deve ter mais de 18 anos.";
        if(!(newGender.equals("Masculino") || newGender.equals("Feminino") || newGender.equals("Outro"))){
            return "Sexo inválido";
        }
        if(newPhone.length() != 11) return "Telefone deve ter 11 digitos.";

        Cliente editedClient = client;
        editedClient.setNome(newName);
        editedClient.setSexo(newGender);
        editedClient.setIdade(Integer.parseInt(newAge));
        editedClient.setTelefone(newPhone);
        return dao.alterar(editedClient);
    }

    public String addPet(String name, String age, String gender, String breed, String size){
        if(name.isEmpty() || age.isEmpty() || breed.isEmpty() || size.isEmpty()) return "Nenhum campo deve estar vazio.";
        if(Integer.parseInt(age) < 0) return "Idade inválida";
        if(!(size.equals("PEQUENO") || size.equals("MEDIO") || size.equals("GRANDE"))){
            return "Porte inválido";
        }

        Porte petSize = Porte.valueOf(size);
        Raca petBreed = Raca.valueOf(breed);
        Pet pet = new Pet(name, Integer.parseInt(age), gender, petBreed, petSize);
        String message = dao.addPet(pet, client);
        if(message.equals("Cliente alterado com sucesso.")) return "Pet adicionado com sucesso.";
        return message;
    }

    public String removePet(String petName){
        if(petName.isEmpty()) return "Nome do pet está vazio.";
        return dao.removePet(client, petName);
    }

    public void setClient(Cliente client){
        this.client = client;
    }
}
