package com.cafemaka.system.service.interfaces;

import com.cafemaka.system.model.Compra;
import com.cafemaka.system.model.dto.CompraDTO;

import java.util.List;

public interface CompraService {
    Compra realizarCompra(CompraDTO request);
    List<Compra> obtenerHistorial(Long clienteId);
}
