package entidades;

public class Estacionamento implements ServicoAdicional {
    private String id;
    private int horas;
    private double valorPorHora;

    public Estacionamento(int horas, double valorPorHora) {
        this.id = "EST" + System.currentTimeMillis();
        this.horas = horas;
        this.valorPorHora = valorPorHora;
    }

    @Override
    public String getId() {
        return id;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public double getValorPorHora() {
        return valorPorHora;
    }

    public void setValorPorHora(double valorPorHora) {
        this.valorPorHora = valorPorHora;
    }

    @Override
    public String getDescricao() {
        return "Estacionamento " + horas + " horas";
    }

    @Override
    public double getValorTotal() {
        return horas * valorPorHora;
    }
}
