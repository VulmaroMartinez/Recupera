package mx.edu.utez.Recupera.service;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.Recupera.config.ApiResponse;
import mx.edu.utez.Recupera.models.User;
import mx.edu.utez.Recupera.models.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;

    /*
    Contraseña (Password): Se debe generar una contraseña aleatoria para cada usuario con las siguientes características:
    Longitud de 8 caracteres.
    Debe incluir caracteres en minúsculas, mayúsculas, números y caracteres especiales.

    Implementa operaciones CRUD para gestionar usuarios.
    La contraseña para cada usuario debe generarse aleatoriamente según los criterios especificados.

     */

    public String generatePassword() {
        String password = "";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String specialCharacters = "!@#$%^&*()_+";
        String allCharacters = lowerCase + upperCase + numbers + specialCharacters;
        for (int i = 0; i < 8; i++) {
            int random = (int) (Math.random() * allCharacters.length());
            password += allCharacters.charAt(random);
        }
        return password;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll() {
        return new ResponseEntity<>(
                new ApiResponse(userRepo.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getByUsername(String username) {
        return new ResponseEntity<>(
                new ApiResponse(userRepo.findByUsername(username), HttpStatus.OK), HttpStatus.OK);
    }


    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> save(User user) {
        Optional<User> foundUser = userRepo.findByUsername(user.getUsername());
        if (foundUser.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "DataAlreadyExists"), HttpStatus.BAD_REQUEST);
        }
        user.setPassword(generatePassword());
        return new ResponseEntity<>(new ApiResponse(userRepo.saveAndFlush(user), HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> update(Long id, User user) {
        Optional<User> foundUser = userRepo.findById(id);
        if (foundUser.isPresent()) {
            Optional<User> existsUser = userRepo.findByUsernameAndIdNot(user.getUsername(), id);
            if (existsUser.isPresent()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "DataAlreadyExists"), HttpStatus.BAD_REQUEST);
            }
            user.setId(id);
            user.setPassword(user.getPassword());
            return new ResponseEntity<>(new ApiResponse(userRepo.saveAndFlush(user), HttpStatus.OK), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "UserNotFound"), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<ApiResponse> deleteById(Long id) {
        Optional<User> foundUser = userRepo.findById(id);
        if (foundUser.isPresent()) {
            userRepo.delete(foundUser.get());
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "UserDeleted"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "UserNotFound"), HttpStatus.BAD_REQUEST);
    }
}
