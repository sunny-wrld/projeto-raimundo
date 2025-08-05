package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Reserva implements Serializable {
    private String id;
    private Cliente cliente;
    private Espaco espaco;
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private List<ServicoAdicional> servicosAdicionais;

    public Reserva(Cliente cliente, Espaco espaco, LocalDate data, LocalTime horaInicio, LocalTime horaFim) {
        this.id = "RE" + System.currentTimeMillis();
        this.cliente = cliente;
        this.espaco = espaco;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.servicosAdicionais = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Espaco getEspaco() {
        return espaco;
    }

    public void setEspaco(Espaco espaco) {
        this.espaco = espaco;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public List<ServicoAdicional> getServicosAdicionais() {
        return servicosAdicionais;
    }

    public void adicionarServico(ServicoAdicional servico) {
        this.servicosAdicionais.add(servico);
    }

    public void removerServico(ServicoAdicional servico) {
        this.servicosAdicionais.remove(servico);
    }

    public double calcularValorTotal() {
        int horasReservadas = calcularHorasDeUso();
        double valorEspaco = horasReservadas * espaco.getValorHora();
        double valorServicos = calcularValorServicos();
        return valorEspaco + valorServicos;
    }

    private int calcularHorasDeUso() {
        int horaInicial = horaInicio.getHour();
        int horaFinal = horaFim.getHour();
        int horas = horaFinal - horaInicial;
        if (horas <= 0) {
            horas = 1;
        }
        return horas;
    }

    private double calcularValorServicos() {
        double total = 0.0;
        for (ServicoAdicional servico : servicosAdicionais) {
            total = total + servico.getValorTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id='" + id + '\'' +
                ", cliente=" + cliente.getNome() +
                ", espaco=" + espaco.getNome() +
                ", data=" + data +
                ", horaInicio=" + horaInicio +
                ", horaFim=" + horaFim +
                ", valorTotal=" + calcularValorTotal() +
                '}';
    }
}