package codex.evaluation.repository;

import codex.evaluation.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement,Integer> {
    List<Paiement> findByClientDevis_IdOrderByDate(int id);
    @Query("SELECT p FROM Paiement p WHERE p.ref_paiement = :ref")
    Paiement findByRefPaiement(@Param("ref") String ref);
}
