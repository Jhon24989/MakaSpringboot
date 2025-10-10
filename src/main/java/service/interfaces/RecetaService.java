package service.interfaces;

import model.Receta;

import java.util.List;

public interface RecetaService {
    List<Receta> listarRecetas();
    Receta obtenerPorId(Long id);
    Receta crear(Receta r);
    Receta actualizar(Long id, Receta r);
    void eliminar(Long id);
}
