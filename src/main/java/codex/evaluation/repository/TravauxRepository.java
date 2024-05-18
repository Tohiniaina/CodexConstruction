package codex.evaluation.repository;

import codex.evaluation.model.Travaux;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TravauxRepository extends JpaRepository<Travaux,Integer> {
    Travaux findByCode(String code);
}
