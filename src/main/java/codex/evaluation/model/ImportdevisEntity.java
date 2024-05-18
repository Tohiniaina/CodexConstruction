package codex.evaluation.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "importdevis")
public class ImportdevisEntity {
    @Id


    private String refDevis;
    private String client;
    private String typeMaison;
    private String finition;
    private String tauxFinition;
    private String dateDevis;
    private String dateDebut;
    private String lieu;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getRefDevis() {
        return refDevis;
    }

    public void setRefDevis(String refDevis) {
        this.refDevis = refDevis;
    }

    public String getTypeMaison() {
        return typeMaison;
    }

    public void setTypeMaison(String typeMaison) {
        this.typeMaison = typeMaison;
    }

    public String getFinition() {
        return finition;
    }

    public void setFinition(String finition) {
        this.finition = finition;
    }

    public String getTauxFinition() {
        return tauxFinition;
    }

    public void setTauxFinition(String tauxFinition) {
        this.tauxFinition = tauxFinition;
    }

    public String getDateDevis() {
        return dateDevis;
    }

    public void setDateDevis(String dateDevis) {
        this.dateDevis = dateDevis;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImportdevisEntity that = (ImportdevisEntity) o;
        return Objects.equals(client, that.client) && Objects.equals(refDevis, that.refDevis) && Objects.equals(typeMaison, that.typeMaison) && Objects.equals(finition, that.finition) && Objects.equals(tauxFinition, that.tauxFinition) && Objects.equals(dateDevis, that.dateDevis) && Objects.equals(dateDebut, that.dateDebut) && Objects.equals(lieu, that.lieu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, refDevis, typeMaison, finition, tauxFinition, dateDevis, dateDebut, lieu);
    }
}
