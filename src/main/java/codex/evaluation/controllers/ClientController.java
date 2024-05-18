package codex.evaluation.controllers;

import codex.evaluation.model.*;
import codex.evaluation.repository.*;
import codex.evaluation.service.PaiementService;
import codex.evaluation.service.UserAdminService;
import codex.evaluation.service.V_ClientDevisService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/codexconstruction/client")
public class ClientController {
    @Autowired
    TypeMaisonRepository typeMaisonRepository;
    @Autowired
    FinitionRepository finitionRepository;
    @Autowired
    ClientDevisRepository clientDevisRepository;
    @Autowired
    V_ClientDevisRepository v_clientDevisRepository;
    @Autowired
    TravauxMaisonRepository travauxMaisonRepository;
    @Autowired
    DetailDevisRepository detailDevisRepository;
    @Autowired
    PaiementRepository paiementRepository;

    @Autowired
    private PaiementService paiementService;
    @Autowired
    private V_ClientDevisService v_clientDevisService;
    @Autowired
    PdfModel pdfModel;

    @GetMapping("/devis")
    public String index(Model model, HttpServletRequest request, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "pageSize", defaultValue = "7") int pageSize){
        HttpSession session = request.getSession();
        UserClient user = (UserClient) session.getAttribute("user");
        int taille = v_clientDevisRepository.countByUserClient_Id(user.getId());
        List<V_ClientDevis> listDevis = v_clientDevisService.findPaginateByIdUser(user.getId(), page-1, pageSize);
        int totalPages = (int)Math.ceil(taille/(double)pageSize);
        System.out.println("Taille : "+taille);
        model.addAttribute("listDevis", listDevis);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("page",page);
        return "Client/devis";
    }
    /*@GetMapping("/devis")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "pageSize", defaultValue = "7") int pageSize){
        List<V_ClientDevis> listDevis = v_clientDevisService.findPaginate(page-1, pageSize);
        int totalPages = (int)Math.ceil(v_clientDevisRepository.count()/(double)pageSize);
        model.addAttribute("listDevis", listDevis);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("page",page);
        return "Admin/Devis";
    }*/

    @GetMapping("/newdevis")
    public String newdevis(Model model){
        List<TypeMaison> typeMaisons = typeMaisonRepository.findAll();
        model.addAttribute("typeMaisons", typeMaisons);
        List<Finition> finitionList = finitionRepository.findAll();
        model.addAttribute("finitionList", finitionList);
        return "Client/nouveauDevis";
    }

    @GetMapping("/paiement")
    public String paiement(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        UserClient user = (UserClient) session.getAttribute("user");
        List<ClientDevis> devisList = clientDevisRepository.findByUserClient_Id(user.getId());
        model.addAttribute("devisList", devisList);
        return "Client/paiement";
    }

    @PostMapping("/payer")
    public ResponseEntity<HashMap<String, String>> payer(Model model,
                                                         @RequestParam("devis") Integer devis,
                                                         @RequestParam("date") String date,
                                                         @RequestParam("montant") Double montant){
        HashMap<String, String> result = new HashMap<>();
        try {
            paiementService.payer(new ClientDevis(devis), Date.valueOf(date), montant);
            result.put("etat", "succes");
        }catch (Exception e) {
            result.put("etat", "error");
            result.put("data", e.getMessage());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/insertdevis")
    @Transactional
    public String insertdevis(HttpServletRequest request, @RequestParam("lieu") String lieu, @RequestParam("typeMaison") String typeMaison, @RequestParam("typeFinition") String typeFinition, @RequestParam("datedebut") String datedebut){
        HttpSession session = request.getSession();
        UserClient user = (UserClient) session.getAttribute("user");

        Date dateDevis = new Date(new java.util.Date().getTime());
        Date dateDebutTravaux = Date.valueOf(datedebut);

        TypeMaison typeMaisonModel = typeMaisonRepository.findById(Integer.parseInt(typeMaison)).get();
        Date dateFinTravaux = Date.valueOf((LocalDate.parse(datedebut).plusDays(typeMaisonModel.getDuree()).toString()));

        Finition finition = finitionRepository.findById(Integer.parseInt(typeFinition)).get();

        List<TravauxMaison> travauxMaison = travauxMaisonRepository.findByTypeMaison_Id(typeMaisonModel.getId());

        ClientDevis clientDevis = new ClientDevis(lieu, user, typeMaisonModel, dateDevis, dateDebutTravaux, dateFinTravaux, finition.getNom(), finition.getPourcentage());

        ClientDevis newClientDevis = clientDevisRepository.save(clientDevis);
        newClientDevis.generateRef();
        newClientDevis = clientDevisRepository.save(newClientDevis);

        for (TravauxMaison tm: travauxMaison) {
            DetailDevis dtv = new DetailDevis(tm.getTravaux().getCode(), newClientDevis, tm.getTravaux().getNom(), tm.getTravaux().getUnite(), tm.getQte(), tm.getTravaux().getPu());
            detailDevisRepository.save(dtv);
        }
        return "redirect:/codexconstruction/client/devis";
    }

    @GetMapping("/detailsdevis")
    public String detailsdevis(Model model, HttpServletResponse response, @RequestParam("devis") String devis){
        List<DetailDevis> listDetails = detailDevisRepository.findByClientDevis_Id(Integer.parseInt(devis));
        List<Paiement> paiementList = paiementRepository.findByClientDevis_IdOrderByDate(Integer.parseInt(devis));
        model.addAttribute("listDetails", listDetails);
        try {
            pdfModel.export(response, listDetails, paiementList);
        } catch (Exception e) {
            return "redirect:/codexconstruction/client/devis";
        }
        return "redirect:/codexconstruction/client/devis";
    }
}
