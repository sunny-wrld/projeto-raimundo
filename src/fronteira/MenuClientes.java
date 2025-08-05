package fronteira;

import controle.AdministradorSistema;
import entidades.Cliente;
import excecoes.ClienteJaCadastradoException;
import excecoes.ClienteNaoEncontradoException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class MenuClientes {
    private AdministradorSistema admin;
    private Scanner scanner;

    public MenuClientes(AdministradorSistema admin, Scanner scanner) {
        this.admin = admin;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            mostrarOpcoes();
            opcao = lerOpcaoDoUsuario();
            executarOpcao(opcao);
        }
    }

    private void mostrarOpcoes() {
        System.out.println("\nMENU DE CLIENTES");
        System.out.println("1. Ver todos os clientes cadastrados");
        System.out.println("2. Cadastrar um novo cliente");
        System.out.println("3. Buscar cliente por CPF");
        System.out.println("4. Remover um cliente");
        System.out.println("0. Voltar ao menu principal");
        System.out.print("Digite sua opcao: ");
    }

    private int lerOpcaoDoUsuario() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private void executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                listarTodosClientes();
                break;
            case 2:
                cadastrarNovoCliente();
                break;
            case 3:
                buscarClientePorCpf();
                break;
            case 4:
                removerCliente();
                break;
            case 0:
                System.out.println("Voltando ao menu principal");
                break;
            default:
                System.out.println("Opcao invalida Digite um numero de 0 a 4");
        }
    }

    private void listarTodosClientes() {
        Map<String, Cliente> clientes = admin.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado ainda");
            return;
        }
        System.out.println("\nLISTA DE CLIENTES");
        for (Cliente cliente : clientes.values()) {
            mostrarDadosCliente(cliente);
        }
    }

    private void cadastrarNovoCliente() {
        System.out.println("\nCADASTRAR NOVO CLIENTE");
        try {
            System.out.print("Digite o CPF (11 numeros): ");
            String cpf = scanner.nextLine();
            if (!validarCpf(cpf)) {
                System.out.println("CPF invalido Deve ter exatamente 11 numeros");
                return;
            }
            System.out.print("Digite o nome: ");
            String nome = scanner.nextLine();
            System.out.print("Digite o email: ");
            String email = scanner.nextLine();
            System.out.print("Digite o telefone: ");
            String telefone = scanner.nextLine();
            LocalDate hoje = LocalDate.now();
            Cliente novoCliente = new Cliente(cpf, nome, email, telefone, hoje);
            admin.cadastrarCliente(novoCliente);
            System.out.println("Cliente cadastrado com sucesso");
        } catch (ClienteJaCadastradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private void buscarClientePorCpf() {
        System.out.println("\nBUSCAR CLIENTE");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        try {
            Cliente cliente = admin.buscarCliente(cpf);
            System.out.println("\nCliente encontrado:");
            mostrarDadosCliente(cliente);
        } catch (ClienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void removerCliente() {
        System.out.println("\nREMOVER CLIENTE");
        System.out.print("Digite o CPF do cliente a ser removido: ");
        String cpf = scanner.nextLine();
        try {
            Cliente cliente = admin.buscarCliente(cpf);
            System.out.println("\nCliente a ser removido:");
            mostrarDadosCliente(cliente);
            System.out.print("Vai remover : sim ou nao ");
            String confirmacao = scanner.nextLine();
            if (confirmacao.equalsIgnoreCase("sim")) {
                admin.removerCliente(cpf);
                System.out.println("Cliente removido com sucesso");
            } else {
                System.out.println("Remocao cancelada");
            }
        } catch (ClienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void mostrarDadosCliente(Cliente cliente) {
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("Data de cadastro: " + cliente.getDataCadastro());
    }

    //gerado pelo sonnet pra ver se tem 11 numeros
    private boolean validarCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        return cpf.length() == 11;
    }
}
