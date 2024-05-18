package codex.evaluation.repository;

import codex.evaluation.model.UserClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserClientRepository extends JpaRepository<UserClient,Integer> {
    Optional<UserClient> findByNumero(String numero);
}
