package mx.edu.utez.Recupera.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Recupera.models.Person;

@Getter
@Setter
@NoArgsConstructor
public class PersonDto {
    private Long id;
    private String name;
    private String surname;
    private String lastname;
    private String email;
    private String number;
    private String address;

    public Person toEntity() {
        return new Person(name, surname, lastname, email, number, address);
    }

}
