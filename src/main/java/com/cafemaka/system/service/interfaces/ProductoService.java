package com.cafemaka.system.service.interfaces;

import com.cafemaka.system.model.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> listar();
    Producto guardar(Producto producto);
    Producto actualizar(Long id, Producto producto);
    void eliminar(Long id);
}
