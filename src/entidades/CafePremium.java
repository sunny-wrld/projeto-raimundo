package entidades;

public class CafePremium implements ServicoAdicional {
    private String id;
    private int quantidade;
    private double valorUnitario;

    public CafePremium(int quantidade, double valorUnitario) {
        this.id = "CP" + System.currentTimeMillis();
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    @Override
    public String getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    @Override
    public String getDescricao() {
        return "Cafe Premium" + quantidade;
    }

    @Override
    public double getValorTotal() {
        return quantidade * valorUnitario;
    }
}