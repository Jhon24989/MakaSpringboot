package controller;

import model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.UsuarioRepository;
import service.interfaces.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin
public class ProductoController {


    private final ProductoService productoService;
    private UsuarioRepository usuarioRepository;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(productoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.listar().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Producto no encontrado")));
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

    @Autowired
    public ProductoController(ProductoService productoService, UsuarioRepository usuarioRepository) {
        this.productoService = productoService;
        this.usuarioRepository = usuarioRepository;
    }
}
