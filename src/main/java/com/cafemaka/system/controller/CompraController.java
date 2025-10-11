package com.cafemaka.system.controller;

import com.cafemaka.system.model.Compra;
import com.cafemaka.system.model.dto.CompraDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cafemaka.system.service.interfaces.CompraService;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin//controla el acceso a la api
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping
    public ResponseEntity<Compra> realizarCompra(@RequestBody CompraDTO request) {
        Compra c = compraService.realizarCompra(request);
        return ResponseEntity.ok(c);
    }

    @GetMapping("/historial/{clienteId}")
    public ResponseEntity<List<Compra>> historial(@PathVariable("clienteId") Long clienteId) {
        return ResponseEntity.ok(compraService.obtenerHistorial(clienteId));
    }
}
