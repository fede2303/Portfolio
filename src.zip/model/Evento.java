package model;

public class Evento {
    private int id;
    private String nome;
    private String data;
    private String luogo;
    private double prezzo;

    public Evento(int id, String nome, String data, String luogo, double prezzo) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.luogo = luogo;
        this.prezzo = prezzo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getData() {
        return data;
    }

    public String getLuogo() {
        return luogo;
    }

    public double getPrezzo() {
        return prezzo;
    }

    @Override
    public String toString() {
        return nome + " - " + data + " - " + luogo + " - €" + prezzo;
    }
}
