package mx.edu.utez.Recupera.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String surname;

    @Column(length = 50, nullable = false)
    private String lastname;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 10, nullable = false)
    private String number;

    @Column(length = 50, nullable = false)
    private String address;

    public Person(String name, String surname, String lastname, String email, String number, String address) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
        this.number = number;
        this.address = address;
    }
}
