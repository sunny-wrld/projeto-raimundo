package entidades;

public class Auditorio extends Espaco {
    public Auditorio(String nome, double valorHora) {
        super("AU" + System.currentTimeMillis(), nome, valorHora);
    }

    @Override
    public String toString() {
        return "Auditorio{" +
                "id='" + getId() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", valorHora=" + getValorHora() +
                '}';
    }
}