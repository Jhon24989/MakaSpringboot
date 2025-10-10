package com.cafemaka.system.service.impl;

import com.cafemaka.system.model.Carrito;
import jakarta.transaction.Transactional;
import com.cafemaka.system.model.ItemCarrito;
import com.cafemaka.system.model.Producto;
import com.cafemaka.system.model.Usuario;
import com.cafemaka.system.repository.CarritoRepository;
import com.cafemaka.system.repository.ProductoRepository;
import com.cafemaka.system.repository.UsuarioRepository;
import com.cafemaka.system.service.interfaces.CarritoService;

public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepo;
    private final UsuarioRepository usuarioRepo;
    private final ProductoRepository productoRepo;

    public CarritoServiceImpl(CarritoRepository carritoRepo, UsuarioRepository usuarioRepo, ProductoRepository productoRepo) {
        this.carritoRepo = carritoRepo;
        this.usuarioRepo = usuarioRepo;
        this.productoRepo = productoRepo;
    }

    @Override
    public Carrito verCarrito(Long usuarioId) {
        return carritoRepo.findByUsuarioId(usuarioId)
                .orElseGet(() -> crearCarrito(usuarioId));
    }

    private Carrito crearCarrito(Long usuarioId) {
        Usuario u = usuarioRepo.findById(usuarioId).orElseThrow();
        Carrito c = new Carrito();
        c.setUsuario(u);
        return carritoRepo.save(c);
    }

    @Override
    @Transactional
    public Carrito agregarProducto(Long usuarioId, Long productoId, int cantidad) {
        Carrito carrito = verCarrito(usuarioId);
        Producto producto = productoRepo.findById(productoId).orElseThrow();

        ItemCarrito itemExistente = carrito.getItems().stream()
                .filter(i -> i.getProducto().getId().equals(productoId))
                .findFirst()
                .orElse(null);

        if (itemExistente != null) {
            itemExistente.setCantidad(itemExistente.getCantidad() + cantidad);
            itemExistente.setSubtotal(producto.getPrecio() * itemExistente.getCantidad());
        } else {
            ItemCarrito nuevo = new ItemCarrito(producto, cantidad);
            nuevo.setCarrito(carrito);
            carrito.getItems().add(nuevo);
        }

        recalcularTotal(carrito);
        return carritoRepo.save(carrito);
    }

    @Override
    @Transactional
    public Carrito modificarCantidad(Long usuarioId, Long productoId, int nuevaCantidad) {
        Carrito carrito = verCarrito(usuarioId);

        carrito.getItems().forEach(item -> {
            if (item.getProducto().getId().equals(productoId)) {
                item.setCantidad(nuevaCantidad);
                item.setSubtotal(item.getProducto().getPrecio() * nuevaCantidad);
            }
        });

        recalcularTotal(carrito);
        return carritoRepo.save(carrito);
    }

    @Override
    @Transactional
    public void eliminarProducto(Long usuarioId, Long productoId) {
        Carrito carrito = verCarrito(usuarioId);
        carrito.getItems().removeIf(item -> item.getProducto().getId().equals(productoId));
        recalcularTotal(carrito);
        carritoRepo.save(carrito);
    }

    private void recalcularTotal(Carrito carrito) {
        double total = carrito.getItems().stream().mapToDouble(ItemCarrito::getSubtotal).sum();
        carrito.setTotal(total);
    }
}


