package view;

import controller.PagamentoController;
import model.Pagamento;

import javax.swing.*;
import java.awt.*;

public class PagamentoFrame extends JFrame {
    private int utenteId;
    private int eventoId;
    private String eventoNome;

    private PagamentoController pagamentoController;

    public PagamentoFrame(int utenteId, int eventoId, String eventoNome) {
        this.utenteId = utenteId;
        this.eventoId = eventoId;
        this.eventoNome = eventoNome;

        pagamentoController = new PagamentoController();

        setTitle("Pagamento");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel infoLabel = new JLabel("<html><center>Evento selezionato:<br><b>" + eventoNome + "</b><br>Prezzo da pagare incluso IVA.</center></html>", SwingConstants.CENTER);
        JButton pagaButton = new JButton("Paga ora");

        setLayout(new BorderLayout());
        add(infoLabel, BorderLayout.CENTER);
        add(pagaButton, BorderLayout.SOUTH);

        pagaButton.addActionListener(e -> {
            Pagamento pagamento = new Pagamento(utenteId, eventoId, 0); // 0 o prezzo reale se lo hai
            boolean successo = pagamentoController.effettuaPagamento(pagamento);

            if (successo) {
                JOptionPane.showMessageDialog(this, "Pagamento effettuato con successo!");

                // Passaggio alla prossima schermata (Scelta gruppo) passando id e nome evento + utenteId
                new SceltaGruppoFrame(eventoId, utenteId).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Errore nel pagamento.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
