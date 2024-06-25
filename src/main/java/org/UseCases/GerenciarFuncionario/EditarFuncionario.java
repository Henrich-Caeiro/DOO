package org.UseCases.GerenciarFuncionario;

import java.util.ArrayList;
import org.DAO.Funcionario.FuncionarioDAO;
import org.model.Funcao;
import org.model.Funcionario;

public class EditarFuncionario {
    FuncionarioDAO funcionarioDAO;

    public EditarFuncionario(FuncionarioDAO funcionarioDAO) {
        this.funcionarioDAO = funcionarioDAO;
    }

    public String editarFuncionario(int id, String newName, String newCpf,
                                    String newAge, String newGender,
                                    String newPhone, String newFuncao, ArrayList<String> newDiasTrabalho,
                                    ArrayList<String> newCargaTrabalho) {
        // Verificar campos obrigatórios
        if (newName.isEmpty() || newPhone.isEmpty() || newGender.isEmpty() || newAge.isEmpty() || newCpf.isEmpty())
            return "Nenhum campo deve estar vazio.";

        // Verificar idade mínima
        if (Integer.parseInt(newAge) < 18)
            return "O funcionário deve ter mais de 18 anos.";

        // Verificar gênero válido
        if (!(newGender.equals("Masculino") || newGender.equals("Feminino") || newGender.equals("Outro")))
            return "Sexo inválido.";

        // Verificar telefone com 11 dígitos
        if (newPhone.length() != 11)
            return "Telefone deve ter 11 dígitos.";

        // Verificar função válida
        Funcao funcao;
        try {
            funcao = Funcao.valueOf(newFuncao);
        } catch (IllegalArgumentException e) {
            return "Função inválida.";
        }

        // Buscar funcionário pelo ID
        Funcionario funcionario = funcionarioDAO.buscarFuncionario(id);
        if (funcionario == null)
            return "Funcionário não encontrado.";

        // Atualizar dados do funcionário
        funcionario.setNome(newName);
        funcionario.setIdade(Integer.parseInt(newAge));
        funcionario.setSexo(newGender);
        funcionario.setTelefone(newPhone);
        funcionario.setFuncao(funcao);
        funcionario.setDiasTrabalho(newDiasTrabalho);
        funcionario.setCargaTrabalho(newCargaTrabalho);

        // Atualizar funcionário no banco de dados
        return funcionarioDAO.atualizarFuncionario(funcionario);
    }
}
