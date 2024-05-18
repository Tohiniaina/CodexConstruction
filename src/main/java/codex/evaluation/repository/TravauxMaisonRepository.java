package codex.evaluation.repository;

import codex.evaluation.model.TravauxMaison;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TravauxMaisonRepository extends JpaRepository<TravauxMaison,Integer> {
    List<TravauxMaison> findByTypeMaison_Id(int id);
}
