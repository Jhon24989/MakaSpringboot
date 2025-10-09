package service.interfaces;

import model.Usuario;

public interface AuthService {
    Usuario register(Usuario usuario);
    Usuario login(String email, String password);

    Usuario obtenerPerfil(Long id);
}
