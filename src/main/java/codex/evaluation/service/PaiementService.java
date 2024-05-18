package codex.evaluation.service;

import codex.evaluation.model.ClientDevis;
import codex.evaluation.model.Paiement;
import codex.evaluation.model.V_ClientDevis;
import codex.evaluation.repository.PaiementRepository;
import codex.evaluation.repository.V_ClientDevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class PaiementService {

    private final PaiementRepository paiementRepository;
    private final V_ClientDevisRepository v_clientDevisRepository;

    @Autowired
    public PaiementService(PaiementRepository paiementRepository, V_ClientDevisRepository v_clientDevisRepository) {
        this.paiementRepository = paiementRepository;
        this.v_clientDevisRepository = v_clientDevisRepository;
    }

    public void payer(ClientDevis devis, Date date, double montant) throws Exception {
        V_ClientDevis vc = v_clientDevisRepository.findById(devis.getId()).get();
        if ((vc.getMontanttotal()-vc.getMontantpaye())<montant) {
            throw new Exception("Montant payement invalide");
        }
        Paiement paiement = new Paiement(devis, date, montant);
        paiement = paiementRepository.save(paiement);
        paiement.generateRef();
        paiementRepository.save(paiement);
    }
}
