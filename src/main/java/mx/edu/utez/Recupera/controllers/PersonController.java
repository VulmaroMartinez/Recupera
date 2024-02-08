package mx.edu.utez.Recupera.controllers;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.Recupera.config.ApiResponse;
import mx.edu.utez.Recupera.models.dto.PersonDto;
import mx.edu.utez.Recupera.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/person")
@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonService ps;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return ps.getAll();
    }

    @GetMapping("/{name}")
    public ResponseEntity<ApiResponse> getByName(@PathVariable String name){
        return ps.getByName(name);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> save( @RequestBody PersonDto personDto) {
        return ps.save(personDto.toEntity()); }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody PersonDto personDto) {
        return ps.update(id, personDto.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        return ps.deleteById(id);
    }
}
