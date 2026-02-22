package application;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.SwingUtilities;
import view.LoginFrame;

public class MainApp {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Connessione al database riuscita!");

                // Avvia la GUI in modo thread-safe
                SwingUtilities.invokeLater(() -> {
                    new LoginFrame();
                });

            } else {
                System.out.println("Connessione al database fallita.");
            }
        } catch (SQLException e) {
            System.out.println("Errore durante la connessione al database.");
            e.printStackTrace();
        }
    }
}
