package view;

import dao.GruppoDAO;
import model.Gruppo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UniscitiGruppoFrame extends JFrame {
    private JList<Gruppo> gruppiList;
    private DefaultListModel<Gruppo> gruppiModel;
    private JButton uniscitiButton;
    private int eventoId;
    private int utenteId;

    public UniscitiGruppoFrame(int eventoId, int utenteId) {
        this.eventoId = eventoId;
        this.utenteId = utenteId;

        setTitle("Unisciti a un gruppo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        gruppiModel = new DefaultListModel<>();
        gruppiList = new JList<>(gruppiModel);
        JScrollPane scrollPane = new JScrollPane(gruppiList);

        uniscitiButton = new JButton("Unisciti al gruppo");

        add(scrollPane, BorderLayout.CENTER);
        add(uniscitiButton, BorderLayout.SOUTH);

        caricaGruppi();

        uniscitiButton.addActionListener(e -> {
            Gruppo gruppoSelezionato = gruppiList.getSelectedValue();
            if (gruppoSelezionato == null) {
                JOptionPane.showMessageDialog(this, "Seleziona un gruppo prima di unirti.", "Attenzione", JOptionPane.WARNING_MESSAGE);
                return;
            }

            GruppoDAO dao = new GruppoDAO();
            boolean successo = dao.uniscitiAlGruppo(gruppoSelezionato.getId(), utenteId);

            if (successo) {
                JOptionPane.showMessageDialog(this, "Ti sei unito al gruppo: " + gruppoSelezionato.getNomeGruppo());
                this.dispose();
                SwingUtilities.invokeLater(() -> new MieiGruppiFrame(utenteId).setVisible(true));
            } else {
                JOptionPane.showMessageDialog(this, "Errore o sei già membro del gruppo.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void caricaGruppi() {
        GruppoDAO dao = new GruppoDAO();
        List<Gruppo> gruppi = dao.getGruppiPerEvento(eventoId);
        gruppiModel.clear();
        for (Gruppo g : gruppi) {
            gruppiModel.addElement(g);
        }
    }
}

