package codex.evaluation.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "importmaisontravaux")
@IdClass(ImportmaisontravauxId.class)
public class ImportmaisontravauxEntity {

    @Id
    private String codeTravaux;

    @Id
    private String typeMaison;

    private String description;
    private String surface;
    private String typeTravaux;
    private String unite;
    private String prixUnitaire;
    private String quantite;
    private String dureeTravaux;

    public ImportmaisontravauxEntity() {}

    public ImportmaisontravauxEntity(String typeMaison, String description, String surface, String codeTravaux, String typeTravaux, String unite, String prixUnitaire, String quantite, String dureeTravaux) {
        this.typeMaison = typeMaison;
        this.description = description;
        this.surface = surface;
        this.codeTravaux = codeTravaux;
        this.typeTravaux = typeTravaux;
        this.unite = unite;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
        this.dureeTravaux = dureeTravaux;
    }

    // Getters and setters

    public String getTypeMaison() {
        return typeMaison;
    }

    public void setTypeMaison(String typeMaison) {
        this.typeMaison = typeMaison;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getCodeTravaux() {
        return codeTravaux;
    }

    public void setCodeTravaux(String codeTravaux) {
        this.codeTravaux = codeTravaux;
    }

    public String getTypeTravaux() {
        return typeTravaux;
    }

    public void setTypeTravaux(String typeTravaux) {
        this.typeTravaux = typeTravaux;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(String prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getDureeTravaux() {
        return dureeTravaux;
    }

    public void setDureeTravaux(String dureeTravaux) {
        this.dureeTravaux = dureeTravaux;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImportmaisontravauxEntity that = (ImportmaisontravauxEntity) o;
        return Objects.equals(typeMaison, that.typeMaison) &&
                Objects.equals(description, that.description) &&
                Objects.equals(surface, that.surface) &&
                Objects.equals(codeTravaux, that.codeTravaux) &&
                Objects.equals(typeTravaux, that.typeTravaux) &&
                Objects.equals(unite, that.unite) &&
                Objects.equals(prixUnitaire, that.prixUnitaire) &&
                Objects.equals(quantite, that.quantite) &&
                Objects.equals(dureeTravaux, that.dureeTravaux);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeMaison, description, surface, codeTravaux, typeTravaux, unite, prixUnitaire, quantite, dureeTravaux);
    }
}
