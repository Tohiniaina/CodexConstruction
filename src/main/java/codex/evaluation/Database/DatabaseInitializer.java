package codex.evaluation.Database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseInitializer {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void executeSqlScript(String createTableScript) {
        Query query = entityManager.createNativeQuery(createTableScript);
        query.executeUpdate();
    }
}
