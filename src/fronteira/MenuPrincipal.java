package fronteira;

import controle.AdministradorSistema;
import java.util.Scanner;

public class MenuPrincipal {
    private AdministradorSistema admin;
    private Scanner scanner;

    public MenuPrincipal(AdministradorSistema admin) {
        this.admin = admin;
        this.scanner = new Scanner(System.in);
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
        System.out.println("\nGerente");
        System.out.println("1. Gerenciar Clientes");
        System.out.println("2. Gerenciar Espacos");
        System.out.println("3. Gerenciar Reservas");
        System.out.println("4. Ver Relatorios");
        System.out.println("0. Sair do sistema");
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
                abrirMenuClientes();
                break;
            case 2:
                abrirMenuEspacos();
                break;
            case 3:
                abrirMenuReservas();
                break;
            case 4:
                abrirMenuRelatorios();
                break;
            case 0:
                System.out.println("Saindo do sistema Ate logo");
                break;
            default:
                System.out.println("Opcao invalida Digite um numero de 0 a 4");
        }
    }

    private void abrirMenuClientes() {
        MenuClientes menuClientes = new MenuClientes(admin, scanner);
        menuClientes.exibirMenu();
    }

    private void abrirMenuEspacos() {
        MenuEspacos menuEspacos = new MenuEspacos(admin, scanner);
        menuEspacos.exibirMenu();
    }

    private void abrirMenuReservas() {
        MenuReservas menuReservas = new MenuReservas(admin, scanner);
        menuReservas.exibirMenu();
    }

    private void abrirMenuRelatorios() {
        MenuRelatorios menuRelatorios = new MenuRelatorios(admin, scanner);
        menuRelatorios.exibirMenu();
    }
}
