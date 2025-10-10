package com.cafemaka.system.service.interfaces;

import com.cafemaka.system.model.Usuario;

public interface AuthService {
    Usuario register(Usuario usuario);
    Usuario login(String email, String password);
    Usuario obtenerPerfil(Long id);
}
