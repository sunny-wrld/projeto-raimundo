package controle;

import entidades.Cliente;
import entidades.Espaco;
import entidades.Reserva;
import entidades.ServicoAdicional;
import excecoes.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class AdministradorSistema implements Serializable {
    private RepositorioClientes repositorioClientes = new RepositorioClientes();
    private RepositorioEspacos repositorioEspacos = new RepositorioEspacos();
    private RepositorioReservas repositorioReservas = new RepositorioReservas();

    public AdministradorSistema() {}

    public void cadastrarCliente(Cliente cliente) throws ClienteJaCadastradoException {
        Cliente clienteExistente = repositorioClientes.buscarCliente(cliente.getCpf());
        if (clienteExistente != null) {
            throw new ClienteJaCadastradoException("Cliente ja cadastrado com este CPF");
        }
        repositorioClientes.adicionarCliente(cliente);
    }

    public Cliente buscarCliente(String cpf) throws ClienteNaoEncontradoException {
        Cliente cliente = repositorioClientes.buscarCliente(cpf);
        if (cliente == null) {
            throw new ClienteNaoEncontradoException("Cliente nao encontrado");
        }
        return cliente;
    }

    public void removerCliente(String cpf) throws ClienteNaoEncontradoException {
        Cliente cliente = repositorioClientes.buscarCliente(cpf);
        if (cliente == null) {
            throw new ClienteNaoEncontradoException("Cliente nao encontrado");
        }
        repositorioClientes.removerCliente(cpf);
    }

    public Map<String, Cliente> listarClientes() {
        return repositorioClientes.listarClientes();
    }

    public void cadastrarEspaco(Espaco espaco) {
        repositorioEspacos.adicionarEspaco(espaco);
    }

    public Espaco buscarEspaco(String id) throws EspacoIndisponivelException {
        Espaco espaco = repositorioEspacos.buscarEspaco(id);
        if (espaco == null) {
            throw new EspacoIndisponivelException("Espaco nao encontrado");
        }
        return espaco;
    }

    public void removerEspaco(String id) throws EspacoIndisponivelException {
        Espaco espaco = repositorioEspacos.buscarEspaco(id);
        if (espaco == null) {
            throw new EspacoIndisponivelException("Espaco nao encontrado");
        }
        repositorioEspacos.removerEspaco(id);
    }

    public Map<String, Espaco> listarEspacos() {
        return repositorioEspacos.listarEspacos();
    }

    public void cadastrarReserva(Reserva reserva) throws EspacoIndisponivelException {
        String idEspaco = reserva.getEspaco().getId();
        Espaco espaco = repositorioEspacos.buscarEspaco(idEspaco);
        if (espaco == null) {
            throw new EspacoIndisponivelException("Espaco nao encontrado");
        }
        repositorioReservas.adicionarReserva(reserva);
    }

    public boolean verificarDisponibilidade(String espacoId, java.time.LocalDate data, java.time.LocalTime inicio, java.time.LocalTime fim) throws EspacoIndisponivelException {
        Espaco espaco = repositorioEspacos.buscarEspaco(espacoId);
        if (espaco == null) {
            throw new EspacoIndisponivelException("Espaco nao encontrado");
        }
        return true;
    }

    public Reserva buscarReserva(String id) throws ReservaNaoEncontradaException {
        Reserva reserva = repositorioReservas.buscarReserva(id);
        if (reserva == null) {
            throw new ReservaNaoEncontradaException("Reserva nao encontrada");
        }
        return reserva;
    }

    public void removerReserva(String id) throws ReservaNaoEncontradaException {
        Reserva reserva = repositorioReservas.buscarReserva(id);
        if (reserva == null) {
            throw new ReservaNaoEncontradaException("Reserva nao encontrada");
        }
        repositorioReservas.removerReserva(id);
    }

    public Map<String, Reserva> listarReservas() {
        return repositorioReservas.listarReservas();
    }

    public void adicionarServicoAReserva(String reservaId, ServicoAdicional servico) throws ReservaNaoEncontradaException, ServicoInvalidoException {
        Reserva reserva = repositorioReservas.buscarReserva(reservaId);
        if (reserva == null) {
            throw new ReservaNaoEncontradaException("Reserva nao encontrada");
        }
        if (servico == null) {
            throw new ServicoInvalidoException("Servico invalido");
        }
        reserva.adicionarServico(servico);
    }

    public void salvarTudo() throws FalhaPersistenciaException {
        repositorioClientes.salvarArquivo("clientes.dat");
        repositorioEspacos.salvarArquivo("espacos.dat");
        repositorioReservas.salvarArquivo("reservas.dat");
    }

    public void carregarTudo() throws FalhaPersistenciaException {
        repositorioClientes.carregarArquivo("clientes.dat");
        repositorioEspacos.carregarArquivo("espacos.dat");
        repositorioReservas.carregarArquivo("reservas.dat");
    }

    public List<Reserva> reservasPorCliente(String cpf) {
        List<Reserva> reservasDoCliente = new ArrayList<>();
        Map<String, Reserva> todasReservas = repositorioReservas.listarReservas();
        for (Reserva reserva : todasReservas.values()) {
            if (reserva.getCliente().getCpf().equals(cpf)) {
                reservasDoCliente.add(reserva);
            }
        }

        return reservasDoCliente;
    }
}