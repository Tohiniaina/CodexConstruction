package codex.evaluation.service;

import codex.evaluation.model.*;
import codex.evaluation.repository.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UtilService {
    private static String uploadDir = "E:\\s6\\Evaluation\\src\\main\\resources\\static\\file";

    private final TypeMaisonRepository typeMaisonRepository;
    private final TravauxRepository travauxRepository;
    private final TravauxMaisonRepository travauxMaisonRepository;
    private final UserClientRepository userClientRepository;
    private final ClientDevisRepository clientDevisRepository;
    private final FinitionRepository finitionRepository;
    private final DetailDevisRepository detailDevisRepository;
    private final PaiementRepository paiementRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UtilService(TypeMaisonRepository typeMaisonRepository, TravauxRepository travauxRepository, TravauxMaisonRepository travauxMaisonRepository, UserClientRepository userClientRepository, ClientDevisRepository clientDevisRepository, FinitionRepository finitionRepository, DetailDevisRepository detailDevisRepository, PaiementRepository paiementRepository) {
        this.typeMaisonRepository = typeMaisonRepository;
        this.travauxRepository = travauxRepository;
        this.travauxMaisonRepository = travauxMaisonRepository;
        this.userClientRepository = userClientRepository;
        this.clientDevisRepository = clientDevisRepository;
        this.finitionRepository = finitionRepository;
        this.detailDevisRepository = detailDevisRepository;
        this.paiementRepository = paiementRepository;
    }

    public static void handleFileUpload(MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                try {
                    // Récupérer le chemin du répertoire où vous souhaitez enregistrer le fichier

                    // Créer le répertoire s'il n'existe pas
                    File uploadDirFile = new File(uploadDir);
                    if (!uploadDirFile.exists()) {
                        uploadDirFile.mkdirs();
                    }

                    // Enregistrer le fichier dans le répertoire spécifié
                    String filePath = uploadDir + File.separator + file.getOriginalFilename();
                    file.transferTo(new File(filePath));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new Exception("File is Empty");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*public static List<String[]> readData(MultipartFile file) throws Exception {
        handleFileUpload(file);
        String path = uploadDir+"\\"+file.getOriginalFilename();
        System.out.println("File Name : "+path);
        List<String[]> data = new ArrayList<>();
        String contentCSV = lireContenuFichier(path);
        String[] ligneContent = contentCSV.split("\n");
        System.out.println("Ligne Contenu : "+ligneContent.length);

        for (int i = 1 ; i < ligneContent.length; i++) {
            String[] contenu = ligneContent[i].split(";");
            data.add(contenu);
        }
        return data;
    }*/

    public static List<String[]> readData(MultipartFile file){
        handleFileUpload(file);
        String path = uploadDir+"\\"+file.getOriginalFilename();
        List<String[]> valiny  = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                valiny.add(nextLine);
            }
            return valiny;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

    }

    @Transactional(rollbackOn = Exception.class)
    public void insert_importMaisonTravaux(MultipartFile file) throws Exception {
        List<String[]> datas = readData(file);
        for (String[] data : datas) {
            String type_maison = data[0];
            String description = data[1];
            String surface = data[2];
            String code_travaux = data[3];
            String type_travaux = data[4];
            String unite = data[5];
            String prix_unitaire = data[6];
            String quantite = data[7];
            String duree_travaux = data[8];
            String sql = "INSERT INTO importmaisontravaux VALUES(:type_maison, :description, :surface, :code_travaux, :type_travaux, :unite, :prix_unitaire, :quantite, :duree_travaux)";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("type_maison", type_maison);
            query.setParameter("description", description);
            query.setParameter("surface", surface);
            query.setParameter("code_travaux", code_travaux);
            query.setParameter("type_travaux", type_travaux);
            query.setParameter("unite", unite);
            query.setParameter("prix_unitaire", prix_unitaire);
            query.setParameter("quantite", quantite);
            query.setParameter("duree_travaux", duree_travaux);
            query.executeUpdate();
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public void insert_importDevis(MultipartFile file) throws Exception {
        List<String[]> datas = readData(file);
        for (String[] data : datas) {
            String client = data[0];
            String ref_devis = data[1];
            String type_maison = data[2];
            String finition = data[3];
            String taux_finition = data[4];
            String date_devis = data[5];
            String date_debut = data[6];
            String lieu = data[7];
            String sql = "INSERT INTO importdevis VALUES(:client, :ref_devis, :type_maison, :finition, :taux_finition, :date_devis, :date_debut, :lieu)";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("client", client);
            query.setParameter("ref_devis", ref_devis);
            query.setParameter("type_maison", type_maison);
            query.setParameter("finition", finition);
            query.setParameter("taux_finition", taux_finition);
            query.setParameter("date_devis", date_devis);
            query.setParameter("date_debut", date_debut);
            query.setParameter("lieu", lieu);
            query.executeUpdate();
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public void insert_importPaiement(MultipartFile file) throws Exception {
        List<String[]> datas = readData(file);
        for (String[] data : datas) {
            String ref_devis = data[0];
            String ref_paiement = data[1];
            String date_paiement = data[2];
            String montant = data[3];
            String sql = "INSERT INTO importpaiement VALUES(:ref_devis, :ref_paiement, :date_paiement, :montant)";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("ref_devis", ref_devis);
            query.setParameter("ref_paiement", ref_paiement);
            query.setParameter("date_paiement", date_paiement);
            query.setParameter("montant", montant);
            query.executeUpdate();
        }
    }

    public void insertCsvImportMaisonTravaux(List<ImportmaisontravauxEntity> datas) throws ParseException {
        for (ImportmaisontravauxEntity data: datas) {

            TypeMaison typeMaison = typeMaisonRepository.findByNom(data.getTypeMaison());
            System.out.println(data.getTypeMaison() + "----" + typeMaison);
            if (typeMaison == null) {
                System.out.println("Vaovao");
                typeMaison = new TypeMaison(data.getTypeMaison(), data.getDescription(), Double.parseDouble(data.getSurface()), Integer.parseInt(data.getDureeTravaux()));
                typeMaison = typeMaisonRepository.save(typeMaison);
            }

            Travaux travaux = travauxRepository.findByCode(data.getCodeTravaux());
            double pu = Double.parseDouble(data.getPrixUnitaire().replace(",", "."));
            if (travaux == null) {
                travaux = new Travaux(data.getTypeTravaux(), data.getCodeTravaux(), data.getUnite(), pu);
                travaux = travauxRepository.save(travaux);
            }
            double qte = Double.parseDouble(data.getQuantite().replace(",", "."));
            TravauxMaison travauxMaison = new TravauxMaison(typeMaison, travaux, qte);
            travauxMaisonRepository.save(travauxMaison);
        }
    }

    public void insertCsvImportDevis(List<ImportdevisEntity> datas) throws ParseException {
        for (ImportdevisEntity data: datas) {

            Optional<UserClient> cli = userClientRepository.findByNumero(data.getClient());
            UserClient userClient = new UserClient();
            if (cli.isEmpty()) {
                userClient = new UserClient(data.getClient());
                userClientRepository.save(userClient);
            } else {
                userClient = cli.get();
            }
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate localDate = LocalDate.parse(normalizeDateString(data.getDateDebut()), formatter);

            TypeMaison typeMaisonModel = typeMaisonRepository.findByNom(data.getTypeMaison());
            Date dateFinTravaux = Date.valueOf(localDate.plusDays(typeMaisonModel.getDuree()).toString());

            List<TravauxMaison> travauxMaison = travauxMaisonRepository.findByTypeMaison_Id(typeMaisonModel.getId());

            Date dateDevis = Date.valueOf(LocalDate.parse(normalizeDateString(data.getDateDevis()),formatter));
            Date dateDebut = Date.valueOf(LocalDate.parse(normalizeDateString(data.getDateDebut()),formatter));

            String percent = data.getTauxFinition().replace("%","");
            percent = percent.replace(",",".");

            Finition finition = finitionRepository.findByNom(data.getFinition());
            if (finition == null) {
                finitionRepository.save(new Finition(data.getFinition(), Double.parseDouble(percent)));
            }

            ClientDevis clientDevis = new ClientDevis(data.getRefDevis(), data.getLieu(), userClient, typeMaisonModel, dateDevis, dateDebut, dateFinTravaux, data.getFinition(), Double.parseDouble(percent));
            ClientDevis newClientDevis = clientDevisRepository.save(clientDevis);

            for (TravauxMaison tm: travauxMaison) {
                DetailDevis dtv = new DetailDevis(tm.getTravaux().getCode(), newClientDevis, tm.getTravaux().getNom(), tm.getTravaux().getUnite(), tm.getQte(), tm.getTravaux().getPu());
                detailDevisRepository.save(dtv);
            }
        }
    }

    public void insertCsvImportPaiement(List<ImportpaiementEntity> datas) throws Exception {
        for (ImportpaiementEntity data: datas) {

            ClientDevis cliDev = clientDevisRepository.findByRef(data.getRefDevis());

            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            // Parse the date string into a java.util.Date object
            Date datePaie = new Date(dateFormat.parse(data.getDatePaiement()).getTime());

            //Date datePaie = Date.valueOf(LocalDate.parse(data.getDatePaiement(),formatter));
            Paiement paiement = paiementRepository.findByRefPaiement(data.getRefPaiement());
            if (paiement == null) {
                paiement = new Paiement(cliDev, data.getRefPaiement(), datePaie, Double.parseDouble(data.getMontant()));
                paiementRepository.save(paiement);
            }
        }
    }

    public <T> void exportToCSV(List<T> data, Class<T> clazz, HttpServletResponse response) throws IOException {
        // Spécifier le nom du fichier CSV à télécharger
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=" + clazz.getSimpleName()+"_data.csv");

        // Utiliser PrintWriter pour écrire dans le flux de réponse
        PrintWriter writer = response.getWriter();

        // Écrire l'en-tête du fichier CSV
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            writer.print(field.getName() + ";");
        }
        writer.println(); // Nouvelle ligne après l'en-tête

        // Écrire les données dans le fichier CSV
        for (T item : data) {
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    writer.print(field.get(item) + ";");
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); // Gérer l'exception selon les besoins
                }
            }
            writer.println(); // Nouvelle ligne après chaque objet
        }

        writer.close();
    }

    private static String normalizeDateString(String dateString) {
        // Split the date string into day, month, and year
        String[] parts = dateString.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        // Reformat the date to ensure single digits are properly handled
        return String.format("%d/%d/%d", day, month, year);
    }

    public boolean checkAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserAdmin admin = (UserAdmin) session.getAttribute("user");
        if (admin == null) {
            return false;
        }
        return true;
    }

    public boolean checkClient(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserClient client = (UserClient) session.getAttribute("user");
        if (client == null) {
            return false;
        }
        return true;
    }
}
