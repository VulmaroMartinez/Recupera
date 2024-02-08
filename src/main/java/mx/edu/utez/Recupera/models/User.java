package mx.edu.utez.Recupera.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 8, nullable = false)
    private String password;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean status;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.status = true;
    }


}
