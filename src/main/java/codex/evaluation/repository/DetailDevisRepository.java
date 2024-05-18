package codex.evaluation.repository;

import codex.evaluation.model.DetailDevis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailDevisRepository extends JpaRepository<DetailDevis,Integer> {
    List<DetailDevis> findByClientDevis_Id(int clientdevis_id);
}
