package service.interfaces;

import model.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> listar();
    Producto guardar(Producto producto);
    Producto actualizar(Long id, Producto producto);
    void eliminar(Long id);
}
