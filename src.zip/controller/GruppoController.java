package controller;

import dao.GruppoDAO;
import model.Gruppo;

import java.util.List;

public class GruppoController {
    private GruppoDAO dao;

    public GruppoController() {
        this.dao = new GruppoDAO();
    }

    // Prende i gruppi di un evento
    public List<Gruppo> getGruppiPerEvento(int eventoId) {
        return dao.getGruppiPerEvento(eventoId);
    }

    // Prende i gruppi di un utente
    public List<Gruppo> getGruppiPerUtente(int utenteId) {
        return dao.getGruppiPerUtente(utenteId);
    }

    // Crea gruppo e aggiunge utente come admin (con descrizione)
    public boolean creaGruppo(String nomeGruppo, String descrizione, int eventoId, int utenteId) {
        Gruppo gruppo = new Gruppo(nomeGruppo, descrizione, eventoId);
        return dao.creaGruppo(gruppo, utenteId);
    }

    // Utente si unisce a gruppo
    public boolean uniscitiAlGruppo(int gruppoId, int utenteId) {
        return dao.uniscitiAlGruppo(gruppoId, utenteId);
    }
}
