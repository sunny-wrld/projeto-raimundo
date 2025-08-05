package entidades;

public class RecebimentoCorrespondencia implements ServicoAdicional {
    private String id;
    private int quantidade;
    private double valorUnitario;

    //usei o sonnet pra criar todos os id unicos
    public RecebimentoCorrespondencia(int quantidade, double valorUnitario) {
        this.id = "RC" + System.currentTimeMillis();
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public String getId() { return id; }

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
        return "Recebimento de Correspondencia" + quantidade +  "id:"  + id ;
    }

    @Override
    public double getValorTotal() {
        return quantidade * valorUnitario;
    }
}
