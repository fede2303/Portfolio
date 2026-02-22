package controller;

import dao.EventoDAO;
import model.Evento;

import java.util.List;

public class EventiController {
    private EventoDAO eventoDAO = new EventoDAO();

    public List<Evento> caricaEventi() {
        return eventoDAO.getTuttiEventi();
    }
}
