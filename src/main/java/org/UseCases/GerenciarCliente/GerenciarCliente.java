package org.UseCases.GerenciarCliente;

import org.DAO.Cliente.ClienteDAO;
import org.model.Cliente;

import java.util.ArrayList;

public class GerenciarCliente {
    Cliente client;
    ClienteDAO clienteDAO = new ClienteDAO();

    public ArrayList<Cliente> getAllClients(){
        return clienteDAO.getAllClients();
    }

    public Cliente getClientFromCpf(String cpf){
        return clienteDAO.getClienteByCpf(cpf);
    }
}
