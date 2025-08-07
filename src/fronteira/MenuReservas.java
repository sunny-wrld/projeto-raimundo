package fronteira;

import controle.AdministradorSistema;
import entidades.Reserva;
import entidades.Cliente;
import entidades.Espaco;
import excecoes.*;
import java.util.Map;
import java.util.Scanner;

public class MenuReservas {
    private AdministradorSistema admin;
    private Scanner scanner;

    public MenuReservas(AdministradorSistema admin, Scanner scanner) {
        this.admin = admin;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\nMenu de Reservas");
            System.out.println("1. Listar reservas");
            System.out.println("2. Cadastrar reserva");
            System.out.println("3. Remover reserva");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opcao: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                opcao = -1;
            }
            switch (opcao) {
                case 1:
                    listarReservas();
                    break;
                case 2:
                    cadastrarReserva();
                    break;
                case 3:
                    removerReserva();
                    break;
                case 0:
                    System.out.println("Voltando");
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }
    }

    private void listarReservas() {
        Map<String, Reserva> reservas = admin.listarReservas();
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada");
            return;
        }
        System.out.println("\nRESERVAS ");
        for (Reserva reserva : reservas.values()) {
            System.out.println("ID: " + reserva.getId());
            System.out.println("Cliente: " + reserva.getCliente().getNome());
            System.out.println("Espaco: " + reserva.getEspaco().getNome());
            System.out.println("Data: " + reserva.getData());
            System.out.println("Hora: " + reserva.getHoraInicio() + reserva.getHoraFim());
            System.out.println("Valor: " + reserva.calcularValorTotal());
        }
    }

    private void cadastrarReserva() {
        try {
            System.out.print("Digite o cpff do cliente: ");
            String cpf = scanner.nextLine();
            Cliente cliente = admin.buscarCliente(cpf);

            System.out.print("Digite o id do espaco: ");
            String espacoId = scanner.nextLine();
            Espaco espaco = admin.buscarEspaco(espacoId);

            //formato bd
            System.out.print("Digite a data da reserva: ");
            String data = scanner.nextLine();
            java.time.LocalDate dataReserva = java.time.LocalDate.parse(data);

            System.out.print("Digite a hora de inicio: ");
            String horaInicio = scanner.nextLine();
            java.time.LocalTime inicio = java.time.LocalTime.parse(horaInicio);

            System.out.print("Digite a hora de fim: ");
            String horaFim = scanner.nextLine();
            java.time.LocalTime fim = java.time.LocalTime.parse(horaFim);

            Reserva reserva = new Reserva(cliente, espaco, dataReserva, inicio, fim);
            admin.cadastrarReserva(reserva);
            System.out.println("Reserva cadastrada");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar reserva: " + e.getMessage());
        }
    }

    private void removerReserva() {
        System.out.print("Digite o id da reserva para remover: ");
        String id = scanner.nextLine();
        try {
            admin.removerReserva(id);
            System.out.println("Reserva removida");
        } catch (ReservaNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }
}
