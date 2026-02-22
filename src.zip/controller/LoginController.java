package controller;

import dao.UtenteDAO;
import model.Utente;

public class LoginController {
    private UtenteDAO utenteDAO;

    public LoginController() {
        this.utenteDAO = new UtenteDAO();
    }

    public Utente eseguiLogin(String username, String password) {
        return utenteDAO.login(username, password);
    }
}
