package codex.evaluation.service;

import codex.evaluation.model.V_ClientDevis;
import codex.evaluation.repository.V_ClientDevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class V_ClientDevisService {
    private V_ClientDevisRepository v_clientDevisRepository;

    @Autowired
    public V_ClientDevisService(V_ClientDevisRepository v_clientDevisRepository) {
        this.v_clientDevisRepository = v_clientDevisRepository;
    }

    public List<V_ClientDevis> findPaginate(int numeroPage, int taille) {
        return v_clientDevisRepository.findAll(PageRequest.of(numeroPage, taille)).getContent();
    }

    public List<V_ClientDevis> findPaginateByIdUser(Long idclient, int numeroPage, int taille) {
        return v_clientDevisRepository.findByUserClient_Id(idclient, PageRequest.of(numeroPage, taille)).getContent();
    }
}
