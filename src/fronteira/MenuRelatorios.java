package fronteira;

import controle.AdministradorSistema;
import entidades.Reserva;
import entidades.Espaco;
import entidades.Cliente;
import entidades.ServicoAdicional;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuRelatorios {
    private AdministradorSistema admin;
    private Scanner scanner;

    public MenuRelatorios(AdministradorSistema admin, Scanner scanner) {
        this.admin = admin;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\nMenu de Relatorios");
            System.out.println("1. Reservas por Cliente");
            System.out.println("2. Utilizacao de Espacos");
            System.out.println("3. Faturamento");
            System.out.println("4. Servicos Adicionais");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opcao: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                opcao = -1;
            }
            switch (opcao) {
                case 1:
                    relatorioReservasPorCliente();
                    break;
                case 2:
                    relatorioUtilizacaoEspacos();
                    break;
                case 3:
                    relatorioFaturamento();
                    break;
                case 4:
                    relatorioServicosAdicionais();
                    break;
                case 0:
                    System.out.println("Voltando");
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }
    }

    private void relatorioReservasPorCliente() {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        List<Reserva> reservas = admin.reservasPorCliente(cpf);
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada para este cliente");
        } else {
            for (Reserva r : reservas) {
                System.out.println("Reserva id: " + r.getId() + " | Espaco: " + r.getEspaco().getNome() + " | Data: " + r.getData());
            }
        }
    }

    private void relatorioUtilizacaoEspacos() {
        Map<String, Espaco> espacos = admin.listarEspacos();
        Map<String, Reserva> reservas = admin.listarReservas();
        for (Espaco e : espacos.values()) {
            int totalReservas = 0;
            for (Reserva r : reservas.values()) {
                if (r.getEspaco().getId().equals(e.getId())) {
                    totalReservas++;
                }
            }
            System.out.println(e.getNome() + " Reservas: " + totalReservas);
        }
    }

    private void relatorioFaturamento() {
        Map<String, Reserva> reservas = admin.listarReservas();
        double total = 0;
        for (Reserva r : reservas.values()) {
            total += r.calcularValorTotal();
        }
        System.out.println("Faturamento total: R$ " + total);
    }

    private void relatorioServicosAdicionais() {
        Map<String, Reserva> reservas = admin.listarReservas();
        int totalServicos = 0;
        double valorServicos = 0;
        for (Reserva r : reservas.values()) {
            for (ServicoAdicional s : r.getServicosAdicionais()) {
                totalServicos++;
                valorServicos += s.getValorTotal();
            }
        }
        System.out.println("Total de servicos adicionais: " + totalServicos);
        System.out.println("Valor arrecadado com servicos: R$ " + valorServicos);
    }
}
