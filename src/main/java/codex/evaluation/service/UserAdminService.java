package codex.evaluation.service;

import codex.evaluation.model.UserAdmin;
import codex.evaluation.model.UserClient;
import codex.evaluation.repository.UserAdminRepository;
import codex.evaluation.repository.UserClientRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAdminService {

    private final UserAdminRepository userAdminRepository;

    @Autowired
    public UserAdminService(UserAdminRepository userAdminRepository) {
        this.userAdminRepository = userAdminRepository;
    }

    public Optional<UserAdmin> findById(int id){
        return userAdminRepository.findById(id);
    }

    public Optional<UserAdmin> findByEmailAndPassword(String email, String password){
        return userAdminRepository.findByEmailAndPassword(email, password);
    }
}
