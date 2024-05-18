package codex.evaluation.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "detaildevis")
public class DetailDevis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "code")
    private String code;
    @ManyToOne
    @JoinColumn(name = "clientdevis_id",referencedColumnName = "id")
    private ClientDevis clientDevis;
    @Column(name = "travaux_nom")
    private String travauxNom;
    @Column(name = "unite")
    private String unite;
    @Column(name = "qte")
    private Double qte;
    @Column(name = "pu")
    private Double pu;

    public DetailDevis() {
    }

    public DetailDevis(String code, ClientDevis clientDevis, String travauxNom, String unite, Double qte, Double pu) {
        this.code = code;
        this.clientDevis = clientDevis;
        this.travauxNom = travauxNom;
        this.unite = unite;
        this.qte = qte;
        this.pu = pu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ClientDevis getClientDevis() {
        return clientDevis;
    }

    public void setClientDevis(ClientDevis clientDevis) {
        this.clientDevis = clientDevis;
    }

    public String getTravauxNom() {
        return travauxNom;
    }

    public void setTravauxNom(String travauxNom) {
        this.travauxNom = travauxNom;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Double getQte() {
        return qte;
    }

    public void setQte(Double qte) {
        this.qte = qte;
    }

    public Double getPu() {
        return pu;
    }

    public void setPu(Double pu) {
        this.pu = pu;
    }
}
