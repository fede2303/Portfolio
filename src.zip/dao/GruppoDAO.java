package dao;

import model.Gruppo;
import database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GruppoDAO {

    // Prende i gruppi a cui un utente appartiene, ora con descrizione
    public List<Gruppo> getGruppiPerUtente(int utenteId) {
        List<Gruppo> gruppi = new ArrayList<>();
        String query = "SELECT g.id, g.nome_gruppo, g.descrizione, g.evento_id " +
                       "FROM gruppi g JOIN membri m ON g.id = m.gruppo_id " +
                       "WHERE m.utente_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, utenteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    gruppi.add(new Gruppo(
                        rs.getInt("id"),
                        rs.getString("nome_gruppo"),
                        rs.getString("descrizione"),
                        rs.getInt("evento_id")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gruppi;
    }

    // Prende i gruppi di un evento (per unirsi), con descrizione
    public List<Gruppo> getGruppiPerEvento(int eventoId) {
        List<Gruppo> gruppi = new ArrayList<>();
        String query = "SELECT id, nome_gruppo, descrizione, evento_id FROM gruppi WHERE evento_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, eventoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    gruppi.add(new Gruppo(
                        rs.getInt("id"),
                        rs.getString("nome_gruppo"),
                        rs.getString("descrizione"),
                        rs.getInt("evento_id")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gruppi;
    }

    // Crea un gruppo con descrizione e inserisce il creatore come membro
    public boolean creaGruppo(Gruppo gruppo, int utenteId) {
        String insertGruppo = "INSERT INTO gruppi (nome_gruppo, descrizione, evento_id) VALUES (?, ?, ?)";
        String insertMembro = "INSERT INTO membri (gruppo_id, utente_id, ruolo) VALUES (?, ?, 'admin')";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psGruppo = conn.prepareStatement(insertGruppo, Statement.RETURN_GENERATED_KEYS)) {
                psGruppo.setString(1, gruppo.getNomeGruppo());
                psGruppo.setString(2, gruppo.getDescrizione());  // qui la descrizione
                psGruppo.setInt(3, gruppo.getEventoId());

                int affectedRows = psGruppo.executeUpdate();

                if (affectedRows == 0) {
                    conn.rollback();
                    return false;
                }

                try (ResultSet generatedKeys = psGruppo.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int gruppoId = generatedKeys.getInt(1);

                        try (PreparedStatement psMembro = conn.prepareStatement(insertMembro)) {
                            psMembro.setInt(1, gruppoId);
                            psMembro.setInt(2, utenteId);
                            psMembro.executeUpdate();
                        }
                    } else {
                        conn.rollback();
                        return false;
                    }
                }
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Aggiunge un utente al gruppo se non è già membro
    public boolean uniscitiAlGruppo(int gruppoId, int utenteId) {
        String checkQuery = "SELECT COUNT(*) FROM membri WHERE gruppo_id = ? AND utente_id = ?";
        String insertQuery = "INSERT INTO membri (gruppo_id, utente_id, ruolo) VALUES (?, ?, 'membro')";

        try (Connection conn = DatabaseConnection.getConnection()) {
            try (PreparedStatement checkPs = conn.prepareStatement(checkQuery)) {
                checkPs.setInt(1, gruppoId);
                checkPs.setInt(2, utenteId);
                try (ResultSet rs = checkPs.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        // già membro
                        return false;
                    }
                }
            }

            try (PreparedStatement insertPs = conn.prepareStatement(insertQuery)) {
                insertPs.setInt(1, gruppoId);
                insertPs.setInt(2, utenteId);
                insertPs.executeUpdate();
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
