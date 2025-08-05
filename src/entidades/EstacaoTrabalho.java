package entidades;

public class EstacaoTrabalho extends Espaco {
    public EstacaoTrabalho(String nome, double valorHora) {
        super("ET" + System.currentTimeMillis(), nome, valorHora);
    }

    @Override
    public String toString() {
        return "EstacaoTrabalho{" +
                "id='" + getId() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", valorHora=" + getValorHora() +
                '}';
    }
}