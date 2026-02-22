package view;

import controller.LoginController;
import model.Utente;
import util.StyleUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;

    private LoginController controller;

    public LoginFrame() {
        controller = new LoginController();

        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        loginButton = new JButton("Login");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.RED);
        gbc.gridy = 3;
        panel.add(statusLabel, gbc);

        add(panel);

        StyleUtil.styleFrame(this);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                statusLabel.setText("Inserisci username e password.");
                return;
            }

            Utente utente = controller.eseguiLogin(username, password);

            if (utente != null) {
                statusLabel.setForeground(Color.GREEN);
                statusLabel.setText("Login effettuato con successo!");

                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if ("admin".equalsIgnoreCase(utente.getRuolo())) {
                            new view.GestioneEventiFrame(utente.getId()).setVisible(true);
                        } else {
                            new view.EventiFrame(utente.getId()).setVisible(true);
                        }
                        dispose();
                    }
                });
                timer.setRepeats(false);
                timer.start();

            } else {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Credenziali errate.");
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
