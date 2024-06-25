package org.controller.Funcionario;

import org.DAO.Funcionario.FuncionarioDAO;
import org.model.Estoque;
import org.model.Funcao;
import org.model.Funcionario;
import org.model.Gerente;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioControllerTest {

    public static void main(String[] args) {

        Estoque estoque = new Estoque();
        // Instanciar o DAO (pode ser um DAO mockado ou uma implementação concreta)
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(); // Supondo que você tem uma implementação concreta

        // Instanciar o controlador
        FuncionarioController funcionarioController = new FuncionarioController(funcionarioDAO);

        ArrayList<String> diasTrabalho = new ArrayList<>();
        diasTrabalho.add("SEGUNDA");
        diasTrabalho.add("TERÇA");
        // Adicione outros dias de trabalho conforme necessário

        // Definindo a carga de trabalho
        ArrayList<String> cargaTrabalho = new ArrayList<>();
        cargaTrabalho.add("2 GRANDE");
        cargaTrabalho.add("1 MÉDIO");
        // Adicionar funcionários
        Gerente gerente1 = new Gerente(1, "João", "M", 30, "123456789", "9999-9999", diasTrabalho, cargaTrabalho);
        gerente1.setDiasTrabalho(new ArrayList<>(List.of("Segunda", "Terça")));
        gerente1.setCargaTrabalho(new ArrayList<>(List.of("08:00-17:00")));

        funcionarioController.adicionarFuncionario(gerente1);



        Funcionario funcionario2 = new Funcionario("João", "M", 12, "50534535", "13563998", diasTrabalho,cargaTrabalho );
        funcionario2.setFuncao(Funcao.ATENDENTE);
        funcionario2.setDiasTrabalho(new ArrayList<>(List.of("Quarta", "Quinta")));
        funcionario2.setCargaTrabalho(new ArrayList<>(List.of("09:00-18:00")));

        funcionarioController.adicionarFuncionario(funcionario2);

        // Atualizar um funcionário
        gerente1.setNome("João da Silva");
        funcionarioController.atualizarFuncionario(gerente1);

        // Buscar um funcionário
        Funcionario funcionarioBuscado = funcionarioController.buscarFuncionario(gerente1.getId());
        System.out.println("Funcionário buscado: " + funcionarioBuscado.getNome());

        // Listar todos os funcionários
        List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
        System.out.println("Lista de funcionários:");
        for (Funcionario f : funcionarios) {
            System.out.println("Nome: " + f.getNome() + ", Função: " + f.getFuncao());
        }

        // Remover um funcionário
        funcionarioController.removerFuncionario(funcionario2.getId());
        System.out.println("Funcionário removido: " + funcionario2.getNome());

        // Listar todos os funcionários novamente
        funcionarios = funcionarioController.listarFuncionarios();
        System.out.println("Lista de funcionários após remoção:");
        for (Funcionario f : funcionarios) {
            System.out.println("Nome: " + f.getNome() + ", Função: " + f.getFuncao());
        }
        // Adicionar produtos ao estoque
        gerente1.adicionaItemEstoque(estoque, "Produto A", 10, 50.0f);
        gerente1.adicionaItemEstoque(estoque, "Produto B", 20, 30.0f);

        // Verificar se os produtos foram adicionados corretamente
        System.out.println("Produtos no Estoque após adição:");
        System.out.println("Produto A - Quantidade: " + estoque.getQtdProduto("Produto A") + ", Preço: " + estoque.getPrecoProduto("Produto A"));
        System.out.println("Produto B - Quantidade: " + estoque.getQtdProduto("Produto B") + ", Preço: " + estoque.getPrecoProduto("Produto B"));

        // Remover alguns produtos do estoque
        gerente1.removeItemEstoque(estoque, "Produto A", 5);

        // Verificar se a remoção foi feita corretamente
        System.out.println("Produtos no Estoque após remoção:");
        System.out.println("Produto A - Quantidade: " + estoque.getQtdProduto("Produto A"));

        // Adicionar produtos com quantidades baixas ao estoque
        gerente1.adicionaItemEstoque(estoque, "Produto C", 2, 100.0f);

        // Verificar se o alerta de baixo estoque é gerado
        estoque.alertaBaixoEstoque("Produto C", 5); // Deve gerar um alerta

        // Adicionar e remover produtos do estoque
        gerente1.adicionaItemEstoque(estoque, "Produto D", 15, 20.0f);
        gerente1.removeItemEstoque(estoque, "Produto D", 10);

        // Verificar se o gerente pode obter o nome de um produto no estoque
        String nomeProduto = gerente1.pegarNomeItemEstoque(estoque, "Produto D");
        System.out.println("Nome do Produto D no estoque: " + nomeProduto);
    }
}
