package codex.evaluation.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "clientdevis")
public class ClientDevis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ref;
    private String lieu;
    @ManyToOne
    @JoinColumn(name = "userclient_id",referencedColumnName = "id")
    private UserClient userClient;
    @ManyToOne
    @JoinColumn(name = "typemaison_id",referencedColumnName = "id")
    private TypeMaison typeMaison;
    @Column(name = "datedevis")
    private Date datedevis;
    @Column(name = "debuttraveaux")
    private Date debuttraveaux;
    @Column(name = "datefintraveaux")
    private Date datefintraveaux;

    @Column(name = "finition_nom")
    private String finition_nom;
    @Column(name = "finition_pourcentage")
    private double finition_pourcentage;

    public ClientDevis() {
    }

    public ClientDevis(String lieu, UserClient userClient, TypeMaison typeMaison, Date datedevis, Date debuttraveaux, Date datefintraveaux, String finition_nom, double finition_pourcentage) {
        this.lieu = lieu;
        this.userClient = userClient;
        this.typeMaison = typeMaison;
        this.datedevis = datedevis;
        this.debuttraveaux = debuttraveaux;
        this.datefintraveaux = datefintraveaux;
        this.finition_nom = finition_nom;
        this.finition_pourcentage = finition_pourcentage;
    }

    public ClientDevis(String ref, String lieu, UserClient userClient, TypeMaison typeMaison, Date datedevis, Date debuttraveaux, Date datefintraveaux, String finition_nom, double finition_pourcentage) {
        this.ref = ref;
        this.lieu = lieu;
        this.userClient = userClient;
        this.typeMaison = typeMaison;
        this.datedevis = datedevis;
        this.debuttraveaux = debuttraveaux;
        this.datefintraveaux = datefintraveaux;
        this.finition_nom = finition_nom;
        this.finition_pourcentage = finition_pourcentage;
    }

    public void generateRef() {
        String idString = String.format("%04d", id);
        this.ref = "D" + idString;
    }
    public ClientDevis(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public UserClient getUserClient() {
        return userClient;
    }

    public void setUserClient(UserClient userClient) {
        this.userClient = userClient;
    }

    public TypeMaison getTypeMaison() {
        return typeMaison;
    }

    public void setTypeMaison(TypeMaison typeMaison) {
        this.typeMaison = typeMaison;
    }

    public Date getDatedevis() {
        return datedevis;
    }

    public void setDatedevis(Date datedevis) {
        this.datedevis = datedevis;
    }

    public Date getDebuttraveaux() {
        return debuttraveaux;
    }

    public void setDebuttraveaux(Date debuttraveaux) {
        this.debuttraveaux = debuttraveaux;
    }

    public Date getDatefintraveaux() {
        return datefintraveaux;
    }

    public void setDatefintraveaux(Date datefintraveaux) {
        this.datefintraveaux = datefintraveaux;
    }

    public String getFinition_nom() {
        return finition_nom;
    }

    public void setFinition_nom(String finition_nom) {
        this.finition_nom = finition_nom;
    }

    public double getFinition_pourcentage() {
        return finition_pourcentage;
    }

    public void setFinition_pourcentage(double finition_pourcentage) {
        this.finition_pourcentage = finition_pourcentage;
    }
}
