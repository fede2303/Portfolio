package model;

public class Gruppo {
    private int id;
    private String nomeGruppo;
    private String descrizione;  // nuovo campo
    private int eventoId;

    // Costruttore completo con id
    public Gruppo(int id, String nomeGruppo, String descrizione, int eventoId) {
        this.id = id;
        this.nomeGruppo = nomeGruppo;
        this.descrizione = descrizione;
        this.eventoId = eventoId;
    }

    // Costruttore senza id (per inserimenti)
    public Gruppo(String nomeGruppo, String descrizione, int eventoId) {
        this.nomeGruppo = nomeGruppo;
        this.descrizione = descrizione;
        this.eventoId = eventoId;
    }

    // getter e setter
    public int getId() {
        return id;
    }

    public String getNomeGruppo() {
        return nomeGruppo;
    }

    public void setNomeGruppo(String nomeGruppo) {
        this.nomeGruppo = nomeGruppo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    @Override
    public String toString() {
        return nomeGruppo;
    }
}
