package dao;

import database.DatabaseConnection;
import model.MessaggioBacheca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BachecaDAO {

    public List<MessaggioBacheca> getMessaggiByGruppo(int gruppoId) {
        List<MessaggioBacheca> messaggi = new ArrayList<>();
        String query = "SELECT b.messaggio, u.username, b.data_pubblicazione " +
                       "FROM Bacheca b JOIN Utenti u ON b.utente_id = u.id " +
                       "WHERE b.gruppo_id = ? ORDER BY b.data_pubblicazione";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, gruppoId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MessaggioBacheca m = new MessaggioBacheca();
                    m.setMessaggio(rs.getString("messaggio"));
                    m.setUsername(rs.getString("username"));
                    m.setDataPubblicazione(rs.getTimestamp("data_pubblicazione"));
                    messaggi.add(m);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return messaggi;
    }

    public boolean inserisciMessaggio(int gruppoId, int utenteId, String testo) {
        String insertQuery = "INSERT INTO Bacheca (gruppo_id, utente_id, messaggio) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertQuery)) {

            ps.setInt(1, gruppoId);
            ps.setInt(2, utenteId);
            ps.setString(3, testo);

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
