package codex.evaluation.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "typemaison")
public class TypeMaison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "description")
    private String description;
    @Column(name = "surface")
    private double surface;
    @Column(name = "duree")
    private int duree;

    public TypeMaison() {
    }

    public TypeMaison(String nom, String description, double surface, int duree) {
        this.nom = nom;
        this.description = description;
        this.surface = surface;
        this.duree = duree;
    }

    public TypeMaison(int id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
}
