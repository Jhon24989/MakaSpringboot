package com.cafemaka.system.controller;

import com.cafemaka.system.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cafemaka.system.service.interfaces.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> register(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(authService.register(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
        Usuario u = authService.login(usuario.getEmail(), usuario.getPassword());
        if (u == null) return ResponseEntity.status(401).build();
        return ResponseEntity.ok(u);
    }

    // NUEVO: ver perfil
    @GetMapping("/perfil/{id}")
    public ResponseEntity<?> verPerfil(@PathVariable("id") Long id) {
        Usuario usuario = authService.obtenerPerfil(id);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "El usuario con ID " + id + " no existe"));
        }
        return ResponseEntity.ok(usuario);
    }

}