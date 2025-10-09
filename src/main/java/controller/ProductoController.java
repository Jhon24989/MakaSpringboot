package controller;

import model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.UsuarioRepository;
import service.interfaces.ProductoService;

import java.util.List;

public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listar() {
        return productoService.listar();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestParam Long adminId, @RequestBody Producto producto) {
        if (!esAdmin(adminId)) return ResponseEntity.status(403).body("No autorizado");
        return ResponseEntity.ok(productoService.guardar(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestParam Long adminId, @RequestBody Producto producto) {
        if (!esAdmin(adminId)) return ResponseEntity.status(403).body("No autorizado");
        return ResponseEntity.ok(productoService.actualizar(id, producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestParam Long adminId) {
        if (!esAdmin(adminId)) return ResponseEntity.status(403).body("No autorizado");
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private boolean esAdmin(Long idUsuario) {
        // Validar desde UsuarioRepository
        return usuarioRepository.findById(idUsuario)
                .map(u -> "ADMIN".equalsIgnoreCase(u.getRol()))
                .orElse(false);
    }


    private final UsuarioRepository usuarioRepository;

    public ProductoController(ProductoService productoService, UsuarioRepository usuarioRepository) {
        this.productoService = productoService;
        this.usuarioRepository = usuarioRepository;
    }
}
