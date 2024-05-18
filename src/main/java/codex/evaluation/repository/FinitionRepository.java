package codex.evaluation.repository;

import codex.evaluation.model.Finition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinitionRepository extends JpaRepository<Finition,Integer> {
    Finition findByNom(String nom);
}
