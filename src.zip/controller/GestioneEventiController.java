package controller;

import dao.EventoDAO;
import model.Evento;

import java.util.List;

public class GestioneEventiController {
    private EventoDAO eventoDAO = new EventoDAO();

    public List<Evento> getTuttiEventi() {
        return eventoDAO.getTuttiEventi();
    }

    public boolean eliminaEvento(int eventoId) {
        return eventoDAO.eliminaEvento(eventoId);
    }

    public boolean aggiungiEvento(String nome, String data, String luogo, double prezzo) {
        return eventoDAO.aggiungiEvento(nome, data, luogo, prezzo);
    }
}
