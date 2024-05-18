package codex.evaluation.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "importpaiement")
public class ImportpaiementEntity {
    @Id


    private String refPaiement;
    private String refDevis;
    private String datePaiement;
    private String montant;

    public String getRefDevis() {
        return refDevis;
    }

    public void setRefDevis(String refDevis) {
        this.refDevis = refDevis;
    }

    public String getRefPaiement() {
        return refPaiement;
    }

    public void setRefPaiement(String refPaiement) {
        this.refPaiement = refPaiement;
    }

    public String getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(String datePaiement) {
        this.datePaiement = datePaiement;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImportpaiementEntity that = (ImportpaiementEntity) o;
        return Objects.equals(refDevis, that.refDevis) && Objects.equals(refPaiement, that.refPaiement) && Objects.equals(datePaiement, that.datePaiement) && Objects.equals(montant, that.montant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refDevis, refPaiement, datePaiement, montant);
    }
}
