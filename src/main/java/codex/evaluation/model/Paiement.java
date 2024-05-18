package codex.evaluation.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "paiement")
public class Paiement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "clientdevis_id",referencedColumnName = "id")
    private ClientDevis clientDevis;
    @Column(name = "ref_paiement")
    private String ref_paiement;
    @Column(name = "date")
    private Date date;
    @Column(name = "montant")
    private double montant;

    public Paiement() {
    }

    public Paiement(ClientDevis clientDevis, String ref_paiement, Date date, double montant) {
        this.clientDevis = clientDevis;
        this.ref_paiement = ref_paiement;
        this.date = date;
        this.montant = montant;
    }

    public Paiement(ClientDevis clientDevis, Date date, double montant) {
        this.clientDevis = clientDevis;
        this.date = date;
        this.montant = montant;
    }

    public void generateRef() {
        String idString = String.format("%04d", id);
        this.ref_paiement = "P" + idString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClientDevis getClientDevis() {
        return clientDevis;
    }

    public void setClientDevis(ClientDevis clientDevis) {
        this.clientDevis = clientDevis;
    }

    public String getRef_paiement() {
        return ref_paiement;
    }

    public void setRef_paiement(String ref_paiement) {
        this.ref_paiement = ref_paiement;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
