package com.cafemaka.system.controller;

import com.cafemaka.system.model.Producto;
import com.cafemaka.system.repository.UsuarioRepository;
import com.cafemaka.system.service.interfaces.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin
public class ProductoController {

    private final ProductoService productoService;
    private final UsuarioRepository usuarioRepository;

    // Inyección de dependencias vía constructor
    @Autowired
    public ProductoController(ProductoService productoService, UsuarioRepository usuarioRepository) {
        this.productoService = productoService;
        this.usuarioRepository = usuarioRepository;
    }

    // -------------------------------
    // MÉTODOS PÚBLICOS DEL CONTROLADOR
    // -------------------------------

    // Listar todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(productoService.listar());
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable Long id) {
        return productoService.listar().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    // Crear un producto (solo admin)
    @PostMapping("/admin/{adminId}")
    public ResponseEntity<?> crear(@PathVariable Long adminId, @RequestBody Producto producto) {
        if (!esAdmin(adminId)) {
            return ResponseEntity.status(403).body("El usuario no es administrador");
        }
        return ResponseEntity.ok(productoService.guardar(producto));
    }

    // Actualizar un producto (solo admin)
    @PutMapping("/{id}/admin/{adminId}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @PathVariable Long adminId,
                                        @RequestBody Producto producto) {
        if (!esAdmin(adminId)) {
            return ResponseEntity.status(403).body("No autorizado");
        }
        return ResponseEntity.ok(productoService.actualizar(id, producto));
    }

    // Eliminar un producto (solo admin)
    @DeleteMapping("/{id}/admin/{adminId}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @PathVariable Long adminId) {
        if (!esAdmin(adminId)) {
            return ResponseEntity.status(403).body("No autorizado");
        }
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // -------------------------------
    // MÉTODOS PRIVADOS AUXILIARES
    // -------------------------------

    // Verifica si el usuario es administrador
    private boolean esAdmin(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .map(u -> "ADMIN".equalsIgnoreCase(u.getRol()))
                .orElse(false);
    }
}


