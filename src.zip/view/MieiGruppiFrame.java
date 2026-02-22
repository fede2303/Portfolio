package view;

import dao.GruppoDAO;
import model.Gruppo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MieiGruppiFrame extends JFrame {
    private int utenteId;

    private JList<Gruppo> gruppiList;
    private DefaultListModel<Gruppo> gruppiModel;
    private JButton apriBachecaButton;
    private JButton indietroButton;

    public MieiGruppiFrame(int utenteId) {
        this.utenteId = utenteId;

        setTitle("I miei gruppi");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        gruppiModel = new DefaultListModel<>();
        gruppiList = new JList<>(gruppiModel);
        JScrollPane scrollPane = new JScrollPane(gruppiList);

        apriBachecaButton = new JButton("Apri Bacheca");
        indietroButton = new JButton("Indietro");

        JPanel bottoniPanel = new JPanel();
        bottoniPanel.add(apriBachecaButton);
        bottoniPanel.add(indietroButton);

        add(scrollPane, BorderLayout.CENTER);
        add(bottoniPanel, BorderLayout.SOUTH);

        caricaGruppi();

        apriBachecaButton.addActionListener(e -> {
            Gruppo gruppoSelezionato = gruppiList.getSelectedValue();
            if (gruppoSelezionato != null) {
                new BachecaFrame(gruppoSelezionato.getId(), utenteId).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Seleziona un gruppo.", "Attenzione", JOptionPane.WARNING_MESSAGE);
            }
        });

        indietroButton.addActionListener(e -> {
            new EventiFrame(utenteId).setVisible(true);
            dispose();
        });
    }

    private void caricaGruppi() {
        GruppoDAO dao = new GruppoDAO();
        List<Gruppo> gruppi = dao.getGruppiPerUtente(utenteId);
        gruppiModel.clear();
        for (Gruppo g : gruppi) {
            gruppiModel.addElement(g);
        }
    }
}
