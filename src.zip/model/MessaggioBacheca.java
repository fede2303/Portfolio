package model;

import java.sql.Timestamp;

public class MessaggioBacheca {
    private String messaggio;
    private String username;
    private Timestamp dataPubblicazione;

    public String getMessaggio() { return messaggio; }
    public void setMessaggio(String messaggio) { this.messaggio = messaggio; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Timestamp getDataPubblicazione() { return dataPubblicazione; }
    public void setDataPubblicazione(Timestamp dataPubblicazione) { this.dataPubblicazione = dataPubblicazione; }
}
