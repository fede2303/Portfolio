package view;

import controller.BachecaController;

import javax.swing.*;
import java.awt.*;

public class BachecaFrame extends JFrame {
    private int gruppoId;
    private int utenteId;

    private JTextArea messaggiArea;
    private JTextField nuovoMessaggioField;
    private JButton inviaButton;

    private BachecaController controller;

    public BachecaFrame(int gruppoId, int utenteId) {
        this.gruppoId = gruppoId;
        this.utenteId = utenteId;

        controller = new BachecaController();

        setTitle("Bacheca del Gruppo");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        messaggiArea = new JTextArea();
        messaggiArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messaggiArea);

        nuovoMessaggioField = new JTextField();
        inviaButton = new JButton("Invia");

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(nuovoMessaggioField, BorderLayout.CENTER);
        bottomPanel.add(inviaButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        caricaMessaggi();

        inviaButton.addActionListener(e -> {
            String testo = nuovoMessaggioField.getText().trim();
            if (!testo.isEmpty()) {
                boolean inviato = controller.inviaMessaggio(gruppoId, utenteId, testo);
                if (inviato) {
                    nuovoMessaggioField.setText("");
                    caricaMessaggi();
                } else {
                    JOptionPane.showMessageDialog(this, "Errore durante l'invio del messaggio.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void caricaMessaggi() {
        messaggiArea.setText("");
        var messaggi = controller.getMessaggi(gruppoId);
        for (var m : messaggi) {
            messaggiArea.append("[" + m.getDataPubblicazione() + "] " + m.getUsername() + ": " + m.getMessaggio() + "\n");
        }
    }
}
