package org.controller;

import org.DAO.LoaderDAO;
import org.controller.Funcionario.FuncionarioControllerTest;

public class Main {
    public static void main(String[] args) {
        //ClienteController clienteTest = new ClienteController();
        //clienteTest.runTest();

        try {
            LoaderDAO loaderDAO = new LoaderDAO();
            //loaderDAO.loadClientData();
            loaderDAO.loadEmployeeData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



        // Testar FuncionarioController
        FuncionarioControllerTest.main(args);
    }
}
