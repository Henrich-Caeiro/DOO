package org.DAO.Funcionario;

import org.model.Funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioDAOInterface {
    void adicionarFuncionario(Funcionario funcionario);
    void atualizarFuncionario(Funcionario funcionario);
    Optional<Funcionario> buscarFuncionarioPorId(int id);
    List<Funcionario> listarTodos();
    List<String> historicoServicoPrestado(int funcionarioId);
    boolean isGerente(int funcionarioId);
}
