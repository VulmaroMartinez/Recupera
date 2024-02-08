package mx.edu.utez.Recupera.service;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.Recupera.config.ApiResponse;
import mx.edu.utez.Recupera.models.Person;
import mx.edu.utez.Recupera.models.PersonRepository;
import mx.edu.utez.Recupera.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepo;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll() {
        return new ResponseEntity<>(
                new ApiResponse(personRepo.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getByName(String name) {
        return new ResponseEntity<>(
                new ApiResponse(personRepo.findByNameIgnoreCase(name), HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> save(Person person) {
        return new ResponseEntity<>(
                new ApiResponse(personRepo.save(person), HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> update(Long id, Person person) {
        Optional<Person> foundPerson = personRepo.findById(id);
        if (foundPerson.isPresent()) {
            person.setId(id);
            return new ResponseEntity<>(
                    new ApiResponse(personRepo.saveAndFlush(person), HttpStatus.OK), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "Person not found"), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<ApiResponse> deleteById(Long id) {
        Optional<Person> foundPerson = personRepo.findById(id);
        if (foundPerson.isPresent()) {
            personRepo.delete(foundPerson.get());
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "PersonDeleted"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "PersonNotFound"), HttpStatus.BAD_REQUEST);
    }


}
