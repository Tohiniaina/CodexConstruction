package codex.evaluation.controllers;

import codex.evaluation.model.DetailDevis;
import codex.evaluation.model.Finition;
import codex.evaluation.model.Travaux;
import codex.evaluation.model.V_ClientDevis;
import codex.evaluation.repository.*;
import codex.evaluation.service.UserAdminService;
import codex.evaluation.service.UtilService;
import codex.evaluation.service.V_ClientDevisService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/codexconstruction/admin")
public class AdminController {

    @Autowired
    V_ClientDevisRepository v_clientDevisRepository;

    @Autowired
    ClientDevisRepository clientDevisRepository;

    @Autowired
    DetailDevisRepository detailDevisRepository;

    @Autowired
    TravauxRepository travauxRepository;

    @Autowired
    FinitionRepository finitionRepository;
    @Autowired
    private V_ClientDevisService v_clientDevisService;
    @Autowired
    private UtilService utilService;

    @GetMapping("/devis")
    public String index(HttpServletRequest request, Model model, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "pageSize", defaultValue = "7") int pageSize){
        boolean author = utilService.checkAdmin(request);
        if (author == false) {
            model.addAttribute("exception", "Access denied");
            return "exception";
        }

        List<V_ClientDevis> listDevis = v_clientDevisService.findPaginate(page-1, pageSize);
        int totalPages = (int)Math.ceil(v_clientDevisRepository.count()/(double)pageSize);
        model.addAttribute("listDevis", listDevis);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("page",page);
        return "Admin/Devis";
    }


    @GetMapping("/detailsdevis")
    public String detailsdevis(HttpServletRequest request, Model model, @RequestParam("devis") String devis){
        boolean author = utilService.checkAdmin(request);
        if (author == false) {
            model.addAttribute("exception", "Access denied");
            return "exception";
        }
        List<DetailDevis> listDetails = detailDevisRepository.findByClientDevis_Id(Integer.parseInt(devis));
        model.addAttribute("listDetails", listDetails);
        return "Admin/DetailsDevis";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request, Model model, @RequestParam(name = "year", defaultValue = "2024" ) int year){
        boolean author = utilService.checkAdmin(request);
        if (author == false) {
            model.addAttribute("exception", "Access denied");
            return "exception";
        }
        double sommeDevis = v_clientDevisRepository.getSommeDevis();
        double sommePaimentDevis = v_clientDevisRepository.getSommePaiementDevis();
        model.addAttribute("sommeDevis", sommeDevis);
        model.addAttribute("sommePaimentDevis", sommePaimentDevis);
        model.addAttribute("year", year);

        String[] data = clientDevisRepository.dataChart(year);
        String result = "[" + String.join(", ", data) + "]";
        model.addAttribute("dataChart", result);

        return "Admin/dashboard";
    }

    @GetMapping("/csvmaisontravaildevis")
    public String csvmaisontravaildevis(HttpServletRequest request, Model model){
        boolean author = utilService.checkAdmin(request);
        if (author == false) {
            model.addAttribute("exception", "Access denied");
            return "exception";
        }

        return "Admin/ImportCsvMaisonTravaux&Devis";
    }

    @GetMapping("/csvpaiement")
    public String csvpaiement(HttpServletRequest request, Model model){
        boolean author = utilService.checkAdmin(request);
        if (author == false) {
            model.addAttribute("exception", "Access denied");
            return "exception";
        }

        double sommeDevis = v_clientDevisRepository.getSommeDevis();
        double sommePaimentDevis = v_clientDevisRepository.getSommePaiementDevis();
        model.addAttribute("sommeDevis", sommeDevis);
        model.addAttribute("sommePaimentDevis", sommePaimentDevis);
        return "Admin/ImportCsvPaiement";
    }

    @GetMapping("/typetravaux")
    public String typetravaux(HttpServletRequest request, Model model){
        boolean author = utilService.checkAdmin(request);
        if (author == false) {
            model.addAttribute("exception", "Access denied");
            return "exception";
        }

        List<Travaux> listTravaux = travauxRepository.findAll();
        model.addAttribute("listTravaux", listTravaux);
        return "Admin/TypeTravaux";
    }

    @PostMapping("/savetravaux")
    public String savetravaux(HttpServletRequest request, Model model, @RequestParam("idTravaux") String idTravaux, @RequestParam("nomTravaux") String nomTravaux, @RequestParam("code") String code, @RequestParam("unite") String unite, @RequestParam("pu") String pu){
        boolean author = utilService.checkAdmin(request);
        if (author == false) {
            model.addAttribute("exception", "Access denied");
            return "exception";
        }

        Travaux travaux = new Travaux(Integer.parseInt(idTravaux), nomTravaux, code, unite, Double.parseDouble(pu));
        travauxRepository.save(travaux);
        return "redirect:/codexconstruction/admin/typetravaux";
    }

    @GetMapping("/typefinition")
    public String typefinition(HttpServletRequest request, Model model){
        boolean author = utilService.checkAdmin(request);
        if (author == false) {
            model.addAttribute("exception", "Access denied");
            return "exception";
        }

        List<Finition> listFinition = finitionRepository.findAll();
        model.addAttribute("listFinition", listFinition);
        return "Admin/TypeFinition";
    }

    @PostMapping("/savefinition")
    public String savefinition(HttpServletRequest request, Model model, @RequestParam("idFinition") String idFinition, @RequestParam("nomFinition") String nomFinition, @RequestParam("pourcentage") String pourcentage){
        boolean author = utilService.checkAdmin(request);
        if (author == false) {
            model.addAttribute("exception", "Access denied");
            return "exception";
        }

        Finition finition = new Finition(Integer.parseInt(idFinition), nomFinition, Double.parseDouble(pourcentage));
        finitionRepository.save(finition);
        return "redirect:/codexconstruction/admin/typefinition";
    }
}
