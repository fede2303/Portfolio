package view;

import dao.GruppoDAO;
import model.Gruppo;

import javax.swing.*;
import java.awt.*;

public class CreaGruppoFrame extends JFrame {
    private JTextField nomeGruppoField;
    private JTextArea descrizioneArea;   // textarea per descrizione
    private JButton creaButton;
    private JButton annullaButton;

    private int eventoId;
    private int utenteId;

    public CreaGruppoFrame(int eventoId, int utenteId) {
        this.eventoId = eventoId;
        this.utenteId = utenteId;

        setTitle("Crea nuovo gruppo");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelInput = new JPanel(new GridLayout(4, 1, 5, 5)); // 4 righe per label+campo
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelInput.add(new JLabel("Nome gruppo:"));
        nomeGruppoField = new JTextField();
        panelInput.add(nomeGruppoField);

        panelInput.add(new JLabel("Descrizione gruppo:"));
        descrizioneArea = new JTextArea(4, 20);
        descrizioneArea.setLineWrap(true);
        descrizioneArea.setWrapStyleWord(true);
        JScrollPane scrollDescrizione = new JScrollPane(descrizioneArea);
        panelInput.add(scrollDescrizione);

        creaButton = new JButton("Crea");
        annullaButton = new JButton("Annulla");

        JPanel panelBottoni = new JPanel();
        panelBottoni.add(creaButton);
        panelBottoni.add(annullaButton);

        add(panelInput, BorderLayout.CENTER);
        add(panelBottoni, BorderLayout.SOUTH);

        creaButton.addActionListener(e -> {
            String nome = nomeGruppoField.getText().trim();
            String descrizione = descrizioneArea.getText().trim();

            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserisci un nome valido per il gruppo.", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (descrizione.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserisci una descrizione per il gruppo.", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Gruppo gruppo = new Gruppo(nome, descrizione, eventoId);  // usa nuovo costruttore con descrizione
            GruppoDAO dao = new GruppoDAO();
            boolean creato = dao.creaGruppo(gruppo, utenteId);

            if (creato) {
                JOptionPane.showMessageDialog(this, "Gruppo creato con successo.");
                this.dispose();
                new MieiGruppiFrame(utenteId).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante la creazione del gruppo.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        annullaButton.addActionListener(e -> {
            this.dispose();
            new EventiFrame(utenteId).setVisible(true);
        });
    }
}
