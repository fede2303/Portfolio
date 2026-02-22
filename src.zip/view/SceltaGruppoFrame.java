package view;

import javax.swing.*;
import java.awt.*;

public class SceltaGruppoFrame extends JFrame {
    private JButton creaGruppoButton;
    private JButton uniscitiGruppoButton;
    private JButton indietroButton;

    private int eventoId;
    private int utenteId;

    public SceltaGruppoFrame(int eventoId, int utenteId) {
        this.eventoId = eventoId;
        this.utenteId = utenteId;

        setTitle("Scegli azione gruppo");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        creaGruppoButton = new JButton("Crea nuovo gruppo");
        uniscitiGruppoButton = new JButton("Unisciti a gruppo esistente");
        indietroButton = new JButton("Indietro");

        add(creaGruppoButton);
        add(uniscitiGruppoButton);
        add(indietroButton);

        creaGruppoButton.addActionListener(e -> {
            this.dispose();
            new CreaGruppoFrame(eventoId, utenteId).setVisible(true);
        });

        uniscitiGruppoButton.addActionListener(e -> {
            this.dispose();
            new UniscitiGruppoFrame(eventoId, utenteId).setVisible(true);
        });

        indietroButton.addActionListener(e -> {
            this.dispose();
            new EventiFrame(utenteId).setVisible(true);
        });
    }
}
