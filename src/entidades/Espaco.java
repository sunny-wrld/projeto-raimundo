package entidades;

import java.io.Serializable;

public abstract class Espaco implements Serializable {
    protected String id;
    protected String nome;
    protected double valorHora;

    public Espaco(String id, String nome, double valorHora) {
        this.id = id;
        this.nome = nome;
        this.valorHora = valorHora;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    @Override
    public String toString() {
        return "Espaco{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", valorHora=" + valorHora +
                '}';
    }
}