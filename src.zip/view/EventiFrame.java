package view;

import controller.EventiController;
import model.Evento;
import util.StyleUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EventiFrame extends JFrame {

    private JList<Evento> eventiList;
    private DefaultListModel<Evento> eventiModel;
    private JButton continuaButton;
    private JButton mieiGruppiButton;
    private JButton indietroButton;
    private int utenteId;

    private EventiController controller;

    public EventiFrame(int utenteId) {
        this.utenteId = utenteId;
        this.controller = new EventiController();

        setTitle("Eventi Disponibili");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        eventiModel = new DefaultListModel<>();
        eventiList = new JList<>(eventiModel);
        JScrollPane scrollPane = new JScrollPane(eventiList);

        continuaButton = new JButton("Continua");
        mieiGruppiButton = new JButton("I miei gruppi");
        indietroButton = new JButton("Indietro");

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(continuaButton);
        bottomPanel.add(mieiGruppiButton);
        bottomPanel.add(indietroButton);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        StyleUtil.styleFrame(this);

        caricaEventi();

        continuaButton.addActionListener(e -> {
            Evento eventoSelezionato = eventiList.getSelectedValue();
            if (eventoSelezionato != null) {
                new PagamentoFrame(utenteId, eventoSelezionato.getId(), eventoSelezionato.toString()).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Seleziona un evento prima di continuare.",
                        "Errore", JOptionPane.WARNING_MESSAGE);
            }
        });

        mieiGruppiButton.addActionListener(e -> {
            this.dispose();
            new MieiGruppiFrame(utenteId).setVisible(true);
        });

        indietroButton.addActionListener(e -> {
            this.dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private void caricaEventi() {
        List<Evento> eventi = controller.caricaEventi();
        eventiModel.clear();
        for (Evento e : eventi) {
            eventiModel.addElement(e);
        }
    }
}


