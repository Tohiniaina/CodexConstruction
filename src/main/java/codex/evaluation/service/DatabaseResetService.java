package codex.evaluation.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class DatabaseResetService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void CleanUpDb() throws IOException {
        String jarPath = "E:\\s6\\Evaluation\\src\\main\\resources\\static\\lib\\gen.jar";
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", jarPath, "evalbtp", "postgres", "postgres", "useradmin");
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        StringBuilder sql = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sql.append(line).append("\n");
        }
        jdbcTemplate.execute(sql.toString());
    }
}

