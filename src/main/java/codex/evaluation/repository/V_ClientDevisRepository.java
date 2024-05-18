package codex.evaluation.repository;

import codex.evaluation.model.V_ClientDevis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface V_ClientDevisRepository extends JpaRepository<V_ClientDevis,Integer> {
    Page<V_ClientDevis> findByUserClient_Id(Long id, Pageable pageable);
    int countByUserClient_Id(Long id);
    List<V_ClientDevis> findById(Long id);

    @Query("SELECT COALESCE(SUM(v.montanttotal),0) FROM V_ClientDevis v")
    double getSommeDevis();

    @Query("SELECT COALESCE(SUM(v.montantpaye),0) FROM V_ClientDevis v")
    double getSommePaiementDevis();

    Page<V_ClientDevis> findAll(Pageable pageable);
}
