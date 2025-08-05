package entidades;

public class SalaPrivada extends Espaco {
    public SalaPrivada(String nome, double valorHora) {
        super("SP" + System.currentTimeMillis(), nome, valorHora);
    }

    @Override
    public String toString() {
        return "SalaPrivada{" +
                "id='" + getId() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", valorHora=" + getValorHora() +
                '}';
    }
}