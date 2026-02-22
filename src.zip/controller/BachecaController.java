package controller;

import dao.BachecaDAO;
import model.MessaggioBacheca;

import java.util.List;

public class BachecaController {
    private BachecaDAO bachecaDAO;

    public BachecaController() {
        bachecaDAO = new BachecaDAO();
    }

    public List<MessaggioBacheca> getMessaggi(int gruppoId) {
        return bachecaDAO.getMessaggiByGruppo(gruppoId);
    }

    public boolean inviaMessaggio(int gruppoId, int utenteId, String testo) {
        return bachecaDAO.inserisciMessaggio(gruppoId, utenteId, testo);
    }
}
