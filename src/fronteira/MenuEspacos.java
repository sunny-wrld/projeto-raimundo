package fronteira;

import controle.AdministradorSistema;
import entidades.Espaco;
import excecoes.EspacoIndisponivelException;
import java.util.Map;
import java.util.Scanner;

public class MenuEspacos {
    private AdministradorSistema admin;
    private Scanner scanner;

    public MenuEspacos(AdministradorSistema admin, Scanner scanner) {
        this.admin = admin;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\nMenu de Espacos");
            System.out.println("1. Listar espacos");
            System.out.println("2. Cadastrar espaco");
            System.out.println("3. Remover espaco");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opcao: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                opcao = -1;
            }
            switch (opcao) {
                case 1:
                    listarEspacos();
                    break;
                case 2:
                    cadastrarEspaco();
                    break;
                case 3:
                    removerEspaco();
                    break;
                case 0:
                    System.out.println("Voltando");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private void listarEspacos() {
        Map<String, Espaco> espacos = admin.listarEspacos();
        if (espacos.isEmpty()) {
            System.out.println("Nenhum espaco cadastrado");
        } else {
            for (Espaco e : espacos.values()) {
                System.out.println(e.getId()  + e.getNome() + " (R$ " + e.getValorHora() + " por h)");
            }
        }
    }

    private void cadastrarEspaco() {
        System.out.println("Digite o tipo (1-Estacao, 2-Sala Privada, 3-Sala Reuniao, 4-Auditorio): ");
        String tipo = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Valor por hora: ");
        double valor = Double.parseDouble(scanner.nextLine());
        Espaco espaco = null;
        switch (tipo) {
            case "1":
                espaco = new entidades.EstacaoTrabalho(nome, valor);
                break;
            case "2":
                espaco = new entidades.SalaPrivada(nome, valor);
                break;
            case "3":
                espaco = new entidades.SalaReuniao(nome, valor);
                break;
            case "4":
                espaco = new entidades.Auditorio(nome, valor);
                break;
            default:
                System.out.println("Tipo invalido!");
                return;
        }
        admin.cadastrarEspaco(espaco);
        System.out.println("Espaco cadastrado com ID: " + espaco.getId());
    }

    private void removerEspaco() {
        System.out.print("Digite o id do espaco para remover: ");
        String id = scanner.nextLine();
        try {
            admin.removerEspaco(id);
            System.out.println("Espaco removido");
        } catch (EspacoIndisponivelException e) {
            System.out.println(e.getMessage());
        }
    }
}