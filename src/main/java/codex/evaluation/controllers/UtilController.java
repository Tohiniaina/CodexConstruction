package codex.evaluation.controllers;

import codex.evaluation.model.*;
import codex.evaluation.model.Error;
import codex.evaluation.repository.ImportDevisRepository;
import codex.evaluation.repository.ImportMaisonTravauxRepository;
import codex.evaluation.repository.ImportPaiementRepository;
import codex.evaluation.service.DatabaseResetService;
import codex.evaluation.service.ErreurService;
import codex.evaluation.service.UtilService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/codexconstruction/util")
public class UtilController {
    @Autowired
    ImportMaisonTravauxRepository importMaisonTravauxRepository;
    @Autowired
    ImportDevisRepository importDevisRepository;
    @Autowired
    ImportPaiementRepository importPaiementRepository;
    private final UtilService utilService;
    private final ErreurService erreurService;
    private final DatabaseResetService databaseResetService;
    @Autowired
    private PdfModel pdfModel;

    @Autowired
    public UtilController(UtilService utilService, ErreurService erreurService, DatabaseResetService databaseResetService) {
        this.utilService = utilService;
        this.erreurService = erreurService;
        this.databaseResetService = databaseResetService;
    }

    @GetMapping("/importcsv")
    public String importcsv() {
        return "Admin/ImportCsv";
    }

    @GetMapping("/databaseconfirm")
    public String databaseconfirm() {
        return "Admin/authDatabase-confirm";
    }

    @PostMapping("/importercsvmaisontravaux&devis")
    public String importercsvmaisontravaux(Model model, @RequestParam("csvmaisontravaux") MultipartFile csvmaisontravaux, @RequestParam("csvdevis") MultipartFile csvdevis) {
        List<Error> errorList = new ArrayList<>();
        System.out.println("Import en cours ...");
        try {
            utilService.insert_importMaisonTravaux(csvmaisontravaux);
            utilService.insert_importDevis(csvdevis);

            List<ImportmaisontravauxEntity> importmaisontravauxEntities = importMaisonTravauxRepository.findAll();
            /*for (ImportmaisontravauxEntity er: importmaisontravauxEntities) {
                System.out.println("Nom"+er.getDescription()+";; Code : "+er.getCodeTravaux());
            }*/

            utilService.insertCsvImportMaisonTravaux(importmaisontravauxEntities);

            List<ImportdevisEntity> importdevisEntities = importDevisRepository.findAll();
            utilService.insertCsvImportDevis(importdevisEntities);
            /*List<Import> dataImport = importRepository.findAll();
            errorList = erreurService.checkError(dataImport);
            System.out.println(errorList.size());
            if (errorList.size() > 0) {
                throw new Exception();
            }
            utilService.insertCsvImport(dataImport);*/
        } catch (Exception e) {
            System.out.println("error : "+e.getMessage());;
            e.printStackTrace();
        }

        return "redirect:/codexconstruction/util/importcsv";
    }

    @PostMapping("/importercsvpaiement")
    public String importercsvpaiement(Model model, @RequestParam("csvpaiement") MultipartFile csvpaiement) {
        List<Error> errorList = new ArrayList<>();
        System.out.println("Import en cours ...");
        try {
            utilService.insert_importPaiement(csvpaiement);

            List<ImportpaiementEntity> importpaiementEntities = importPaiementRepository.findAll();
            for (ImportpaiementEntity importpaiementEntity: importpaiementEntities) {
                System.out.println("Paiement : "+importpaiementEntity.getRefPaiement());
            }
            utilService.insertCsvImportPaiement(importpaiementEntities);
            /*List<Import> dataImport = importRepository.findAll();
            errorList = erreurService.checkError(dataImport);
            System.out.println(errorList.size());
            if (errorList.size() > 0) {
                throw new Exception();
            }
            utilService.insertCsvImport(dataImport);*/
        } catch (Exception e) {
            System.out.println("error : "+e.getMessage());;
            e.printStackTrace();
        }

        return "redirect:/codexconstruction/util/importcsv";
    }

    @PostMapping("/readcsv")
    public String importData(Model model, @RequestParam("filecsv") MultipartFile file) {
        try {
            List<String[]> data = utilService.readData(file);
            model.addAttribute("data", data);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            e.printStackTrace();
        }
        return "admin/ResultDataCsv";
    }

    @GetMapping("/exportCSV")
    public void exportData(HttpServletResponse response) throws IOException {
        // Créer des données de test (remplacez cela par vos propres données)
        /*List<Person> people = new ArrayList<>();
        people.add(new Person("John Doe", 30));
        people.add(new Person("Jane Doe", 25));
        people.add(new Person("Bob Smith", 40));

        // Appeler la méthode de la classe de service pour effectuer l'exportation
        utilService.exportToCSV(people, Person.class, response);*/
    }

    @PostMapping("/exportPDF")
    public void exportDataPDF(HttpServletResponse response) throws IOException {
        //List<Modele> modeles = modeleService.findAll();
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=modeles_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        //pdfModel.export(response, modeles);
    }

    @GetMapping("/resetDatabase")
    public String resetDatabase(Model model, HttpServletRequest request) throws IOException {
        databaseResetService.CleanUpDb();
        HttpSession session = request.getSession();
        model.addAttribute("user", session.getAttribute("user"));
        return "Admin/home";
    }
}
