package codex.evaluation.model;

import java.io.Serializable;
import java.util.Objects;

public class ImportmaisontravauxId implements Serializable {
    private String codeTravaux;
    private String typeMaison;

    // Default constructor
    public ImportmaisontravauxId() {}

    // Parameterized constructor
    public ImportmaisontravauxId(String codeTravaux, String typeMaison) {
        this.codeTravaux = codeTravaux;
        this.typeMaison = typeMaison;
    }

    // Getters and setters
    public String getCodeTravaux() {
        return codeTravaux;
    }

    public void setCodeTravaux(String codeTravaux) {
        this.codeTravaux = codeTravaux;
    }

    public String getTypeMaison() {
        return typeMaison;
    }

    public void setTypeMaison(String typeMaison) {
        this.typeMaison = typeMaison;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImportmaisontravauxId that = (ImportmaisontravauxId) o;
        return Objects.equals(codeTravaux, that.codeTravaux) &&
                Objects.equals(typeMaison, that.typeMaison);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeTravaux, typeMaison);
    }
}
