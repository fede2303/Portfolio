package dao;

import model.Evento;
import database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    public List<Evento> getTuttiEventi() {
        List<Evento> eventi = new ArrayList<>();
        String query = "SELECT id, nome, data_evento, luogo, prezzo FROM eventi ORDER BY data_evento";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String data = rs.getString("data_evento");
                String luogo = rs.getString("luogo");
                double prezzo = rs.getDouble("prezzo");

                eventi.add(new Evento(id, nome, data, luogo, prezzo));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventi;
    }

    public boolean eliminaEvento(int eventoId) {
        String query = "DELETE FROM eventi WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, eventoId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean aggiungiEvento(String nome, String data, String luogo, double prezzo) {
        String query = "INSERT INTO eventi (nome, data_evento, luogo, prezzo) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nome);
            ps.setString(2, data);
            ps.setString(3, luogo);
            ps.setDouble(4, prezzo);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
