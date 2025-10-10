package service.interfaces;

import model.Carrito;

public interface CarritoService {

    Carrito verCarrito(Long usuarioId);
    Carrito agregarProducto(Long usuarioId, Long productoId, int cantidad);
    Carrito modificarCantidad(Long usuarioId, Long productoId, int nuevaCantidad);
    void eliminarProducto(Long usuarioId, Long productoId);
}


