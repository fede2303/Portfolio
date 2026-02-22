package dao;

import database.DatabaseConnection;
import model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UtenteDAO {

    public Utente login(String username, String password) {
        String query = "SELECT * FROM Utenti WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Utente utente = new Utente();
                    utente.setId(rs.getInt("id"));
                    utente.setUsername(rs.getString("username"));
                    utente.setRuolo(rs.getString("ruolo"));
                    // altri campi se servono
                    return utente;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

