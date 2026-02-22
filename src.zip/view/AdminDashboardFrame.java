package view;

import javax.swing.*;
import java.awt.*;

public class AdminDashboardFrame extends JFrame {
    private int adminId;

    private JButton gestioneEventiButton;
    private JButton logoutButton;

    public AdminDashboardFrame(int adminId) {
        this.adminId = adminId;

        setTitle("Dashboard Amministratore");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gestioneEventiButton = new JButton("Gestisci Eventi");
        logoutButton = new JButton("Logout");

        gestioneEventiButton.addActionListener(e -> {
            new GestioneEventiFrame(adminId).setVisible(true);
            this.dispose();
        });

        logoutButton.addActionListener(e -> {
            // Torna al login
            new LoginFrame().setVisible(true);
            this.dispose();
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.add(gestioneEventiButton);
        panel.add(logoutButton);
        
        

        add(panel);
    }
}
