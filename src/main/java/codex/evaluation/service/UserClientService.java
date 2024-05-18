package codex.evaluation.service;

import codex.evaluation.model.UserAdmin;
import codex.evaluation.model.UserClient;
import codex.evaluation.repository.UserClientRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserClientService {

    private final UserClientRepository userClientRepository;

    @Autowired
    public UserClientService(UserClientRepository userClientRepository) {
        this.userClientRepository = userClientRepository;
    }

    public List<UserClient> findall(){
        return userClientRepository.findAll();
    }
    public UserClient save(UserClient user){
        return userClientRepository.save(user);
    }

    public Optional<UserClient> findById(int id){
        return userClientRepository.findById(id);
    }
    public Optional<UserClient> findByNumero(String numero){
        return userClientRepository.findByNumero(numero);
    }
}
