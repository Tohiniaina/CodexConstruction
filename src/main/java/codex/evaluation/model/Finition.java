package codex.evaluation.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "finition")
public class Finition {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "pourcentage")
    private Double pourcentage;

    public Finition() {
    }

    public Finition(int id, String nom, Double pourcentage) {
        this.id = id;
        this.nom = nom;
        this.pourcentage = pourcentage;
    }

    public Finition(String nom, Double pourcentage) {
        this.nom = nom;
        this.pourcentage = pourcentage;
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

    public Double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Double pourcentage) {
        this.pourcentage = pourcentage;
    }
}
