package dao;

import java.sql.*;

import database.DatabaseConnection;

public class MembroDAO {

    public void aggiungiCreatore(int gruppoId, int utenteId) throws SQLException {
        String insertMembro = "INSERT INTO membri (gruppo_id, utente_id, ruolo) VALUES (?, ?, 'creatore')";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertMembro)) {
            ps.setInt(1, gruppoId);
            ps.setInt(2, utenteId);
            ps.executeUpdate();
        }
    }
}
