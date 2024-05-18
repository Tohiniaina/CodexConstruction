package codex.evaluation.repository;

import codex.evaluation.model.TypeMaison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TypeMaisonRepository extends JpaRepository<TypeMaison,Integer> {
    TypeMaison findByNomAndDescriptionAndSurfaceAndDuree(String nom, String description, double surface, int duree);
    @Query("SELECT ty FROM TypeMaison ty WHERE ty.nom LIKE :nom")
    TypeMaison findByNom(@Param("nom") String nom);
}
