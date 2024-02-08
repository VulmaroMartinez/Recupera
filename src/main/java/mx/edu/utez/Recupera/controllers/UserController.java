package mx.edu.utez.Recupera.controllers;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.Recupera.config.ApiResponse;
import mx.edu.utez.Recupera.models.dto.UserDto;
import mx.edu.utez.Recupera.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse> save(@RequestBody UserDto dto) {
        return userService.save(dto.toEntity());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody UserDto dto) {
        return userService.update(id, dto.toEntity());
    }
    @GetMapping("/{username}")
    public ResponseEntity<ApiResponse> findByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> findAll() {
        return userService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        return userService.deleteById(id);
    }


}
