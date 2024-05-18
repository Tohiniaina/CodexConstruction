package codex.evaluation.repository;

import codex.evaluation.model.ImportmaisontravauxEntity;
import codex.evaluation.model.ImportmaisontravauxId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportMaisonTravauxRepository extends JpaRepository<ImportmaisontravauxEntity, ImportmaisontravauxId> {
    // Vous pouvez ajouter des méthodes de requête personnalisées ici si nécessaire
}
