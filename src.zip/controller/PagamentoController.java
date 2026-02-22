package controller;

import model.Pagamento;

public class PagamentoController {
    public boolean effettuaPagamento(Pagamento pagamento) {
        // Qui si potrebbe integrare la logica di pagamento reale (API, DB, ecc)
        // Per ora simuliamo successo sempre
        pagamento.setStato("EFFETTUATO");
        return true;
    }
}
