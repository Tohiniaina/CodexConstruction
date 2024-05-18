package codex.evaluation.repository;

import codex.evaluation.model.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAdminRepository extends JpaRepository<UserAdmin,Integer> {

    @Query("""
        SELECT
            u
        FROM UserAdmin u
        WHERE u.email = :email AND u.password = :password
    """)
    Optional<UserAdmin> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
