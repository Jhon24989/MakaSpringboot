package com.cafemaka.system.controller;

import com.cafemaka.system.model.Carrito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cafemaka.system.service.interfaces.CarritoService;

public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Carrito> ver(@PathVariable Long usuarioId) {//odtiene valores desde la url
        return ResponseEntity.ok(carritoService.verCarrito(usuarioId));
    }

    @PostMapping("/{usuarioId}/agregar/{productoId}")
    public ResponseEntity<Carrito> agregar(@PathVariable Long usuarioId, @PathVariable Long productoId, @RequestParam int cantidad) {
        return ResponseEntity.ok(carritoService.agregarProducto(usuarioId, productoId, cantidad));
    }

    @PutMapping("/{usuarioId}/modificar/{productoId}")
    public ResponseEntity<Carrito> modificar(@PathVariable Long usuarioId, @PathVariable Long productoId, @RequestParam int cantidad) {
        return ResponseEntity.ok(carritoService.modificarCantidad(usuarioId, productoId, cantidad));
    }

    @DeleteMapping("/{usuarioId}/eliminar/{productoId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long usuarioId, @PathVariable Long productoId) {
        carritoService.eliminarProducto(usuarioId, productoId);
        return ResponseEntity.noContent().build();
    }
}


