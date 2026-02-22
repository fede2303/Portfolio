package view;

import controller.GestioneEventiController;
import model.Evento;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GestioneEventiFrame extends JFrame {
    private int adminId;

    private DefaultListModel<Evento> eventiModel;
    private JList<Evento> eventiList;
    private JButton aggiungiButton;
    private JButton eliminaButton;
    private JButton indietroButton;

    private GestioneEventiController controller;

    public GestioneEventiFrame(int adminId) {
        this.adminId = adminId;
        controller = new GestioneEventiController();

        setTitle("Gestione Eventi");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        eventiModel = new DefaultListModel<>();
        eventiList = new JList<>(eventiModel);
        JScrollPane scrollPane = new JScrollPane(eventiList);

        aggiungiButton = new JButton("Crea Evento");
        eliminaButton = new JButton("Rimuovi Evento");
        indietroButton = new JButton("Indietro");

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(aggiungiButton);
        buttonsPanel.add(eliminaButton);
        buttonsPanel.add(indietroButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        caricaEventi();

        aggiungiButton.addActionListener(e -> mostraDialogAggiungiEvento());

        eliminaButton.addActionListener(e -> {
            Evento eventoSelezionato = eventiList.getSelectedValue();
            if (eventoSelezionato != null) {
                int conferma = JOptionPane.showConfirmDialog(this,
                        "Sei sicuro di voler eliminare l'evento:\n" + eventoSelezionato,
                        "Conferma eliminazione",
                        JOptionPane.YES_NO_OPTION);
                if (conferma == JOptionPane.YES_OPTION) {
                    if (controller.eliminaEvento(eventoSelezionato.getId())) {
                        JOptionPane.showMessageDialog(this, "Evento eliminato con successo.");
                        caricaEventi();
                    } else {
                        JOptionPane.showMessageDialog(this, "Errore durante l'eliminazione.", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleziona un evento da eliminare.", "Attenzione", JOptionPane.WARNING_MESSAGE);
            }
        });

        indietroButton.addActionListener(e -> {
            new AdminDashboardFrame(adminId).setVisible(true);
            this.dispose();
        });
    }

    private void caricaEventi() {
        eventiModel.clear();
        List<Evento> eventi = controller.getTuttiEventi();
        for (Evento evento : eventi) {
            eventiModel.addElement(evento);
        }
    }

    private void mostraDialogAggiungiEvento() {
        JTextField nomeField = new JTextField();
        JTextField dataField = new JTextField("YYYY-MM-DD");
        JTextField luogoField = new JTextField();
        JTextField prezzoField = new JTextField();

        Object[] message = {
                "Nome evento:", nomeField,
                "Data evento (YYYY-MM-DD):", dataField,
                "Luogo:", luogoField,
                "Prezzo (es. 10.50):", prezzoField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Aggiungi nuovo evento", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText().trim();
            String data = dataField.getText().trim();
            String luogo = luogoField.getText().trim();
            String prezzoStr = prezzoField.getText().trim();

            if (nome.isEmpty() || data.isEmpty() || luogo.isEmpty() || prezzoStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tutti i campi sono obbligatori.", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double prezzo = Double.parseDouble(prezzoStr);
                if (controller.aggiungiEvento(nome, data, luogo, prezzo)) {
                    JOptionPane.showMessageDialog(this, "Evento aggiunto con successo.");
                    caricaEventi();
                } else {
                    JOptionPane.showMessageDialog(this, "Errore durante l'aggiunta evento.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Prezzo non valido.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
