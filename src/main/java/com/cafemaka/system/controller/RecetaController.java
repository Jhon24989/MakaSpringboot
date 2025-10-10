package com.cafemaka.system.controller;

import com.cafemaka.system.model.Receta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cafemaka.system.service.interfaces.RecetaService;

import java.util.List;

@RestController
@RequestMapping("/api/recetas")
@CrossOrigin
public class RecetaController {

    private final RecetaService recetaService;

    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @GetMapping
    public ResponseEntity<List<Receta>> listar() {
        return ResponseEntity.ok(recetaService.listarRecetas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(recetaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Receta> crear(@RequestBody Receta receta) {
        return ResponseEntity.ok(recetaService.crear(receta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receta> actualizar(@PathVariable Long id, @RequestBody Receta receta) {
        return ResponseEntity.ok(recetaService.actualizar(id, receta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        recetaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
