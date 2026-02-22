package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/TT?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";   // Cambia con il tuo username MySQL
    private static final String PASSWORD = "Ciao123!"; // Cambia con la tua password MySQL

    public static Connection getConnection() {
        try {
            // Carica il driver MySQL (non sempre necessario con JDBC 4+ ma utile per sicurezza)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Ottieni la connessione
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL non trovato.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Errore nella connessione al database.");
            e.printStackTrace();
            return null;
        }
    }
}
