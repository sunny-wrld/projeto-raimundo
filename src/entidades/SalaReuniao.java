package entidades;

public class SalaReuniao extends Espaco {
    public SalaReuniao(String nome, double valorHora) {
        super("SR" + System.currentTimeMillis(), nome, valorHora);
    }

    @Override
    public String toString() {
        return "SalaReuniao{" +
                "id='" + getId() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", valorHora=" + getValorHora() +
                '}';
    }
}