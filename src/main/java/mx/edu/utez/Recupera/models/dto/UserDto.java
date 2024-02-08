package mx.edu.utez.Recupera.models.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Recupera.models.User;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;

    private String username;

    private String password;

    private boolean status;

    public User toEntity(){
        return new User( username, password );
    }
}
