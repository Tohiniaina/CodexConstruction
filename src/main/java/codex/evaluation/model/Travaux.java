package codex.evaluation.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "travaux", catalog = "evalbtp")
public class Travaux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nom")
    private String nom;
    private String code;
    @Column(name = "unite")
    private String unite;
    @Column(name = "pu")
    private Double pu;

    public Travaux() {
    }

    public Travaux(int id, String nom, String code, String unite, Double pu) {
        this.id = id;
        this.nom = nom;
        this.code = code;
        this.unite = unite;
        this.pu = pu;
    }

    public Travaux(String nom, String code, String unite, Double pu) {
        this.nom = nom;
        this.code = code;
        this.unite = unite;
        this.pu = pu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Double getPu() {
        return pu;
    }

    public void setPu(Double pu) {
        this.pu = pu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travaux that = (Travaux) o;
        return id == that.id && Objects.equals(nom, that.nom) && Objects.equals(unite, that.unite) && Objects.equals(pu, that.pu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, unite, pu);
    }
}
