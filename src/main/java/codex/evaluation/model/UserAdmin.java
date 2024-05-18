package codex.evaluation.model;


import jakarta.persistence.*;


@Entity
@Table(name = "useradmin")
public class UserAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    public UserAdmin() {
    }

    public UserAdmin(String nom, String email,String mdp) {
        this.id = id;
        this.username = nom;
        this.email = email;
        this.password = mdp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
