package codex.evaluation.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "travauxmaison")
public class TravauxMaison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "typemaison_id",referencedColumnName = "id")
    private TypeMaison typeMaison;

    @ManyToOne
    @JoinColumn(name = "traveau_id",referencedColumnName = "id")
    private Travaux travaux;
    @Column(name = "qte")
    private Double qte;

    public TravauxMaison() {
    }

    public TravauxMaison(TypeMaison typeMaison, Travaux travaux, Double qte) {
        this.typeMaison = typeMaison;
        this.travaux = travaux;
        this.qte = qte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeMaison getTypeMaison() {
        return typeMaison;
    }

    public void setTypeMaison(TypeMaison typeMaison) {
        this.typeMaison = typeMaison;
    }

    public Travaux getTravaux() {
        return travaux;
    }

    public void setTravaux(Travaux travaux) {
        this.travaux = travaux;
    }

    public Double getQte() {
        return qte;
    }

    public void setQte(Double qte) {
        this.qte = qte;
    }
}
