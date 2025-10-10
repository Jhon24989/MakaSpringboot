package com.cafemaka.system.service.impl;

import com.cafemaka.system.model.Usuario;
import org.springframework.stereotype.Service;
import com.cafemaka.system.repository.UsuarioRepository;
import com.cafemaka.system.service.interfaces.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario register(Usuario usuario) {
        if (usuario.getEmail().endsWith("@admin.com")) {
            usuario.setRol("ADMIN");
        } else {
            usuario.setRol("USER");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario login(String email, String password) {
        return usuarioRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
    }

    @Override
    public Usuario obtenerPerfil(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }
}
