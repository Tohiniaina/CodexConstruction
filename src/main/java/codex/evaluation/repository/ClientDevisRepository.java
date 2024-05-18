package codex.evaluation.repository;

import codex.evaluation.model.ClientDevis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientDevisRepository extends JpaRepository<ClientDevis,Integer> {
    List<ClientDevis> findByUserClient_Id(Long id);
    ClientDevis findByRef(String ref);

    @Query( value = """
        WITH tous_les_mois AS (
            SELECT generate_series(1, 12) AS mois
        )
        SELECT
            COALESCE(SUM(vd.total), 0) AS total
        FROM tous_les_mois tm
                 LEFT JOIN v_devisstat vd ON tm.mois = vd.mois AND vd.annee = ?1
        GROUP BY tm.mois
        ORDER BY tm.mois;
    """, nativeQuery = true)
    String[] dataChart(int Year);
}
