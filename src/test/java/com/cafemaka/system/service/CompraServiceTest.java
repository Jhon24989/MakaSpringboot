package com.cafemaka.system.service;

import com.cafemaka.system.model.*;
import com.cafemaka.system.model.dto.*;
import com.cafemaka.system.repository.*;
import com.cafemaka.system.service.impl.CompraServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class CompraServiceTest {

    private CompraRepository mockCompraRepo;
    private UsuarioRepository mockUsuarioRepo;
    private ProductoRepository mockProductoRepo;
    private CompraServiceImpl service;

    @BeforeEach
    void setup() {
        mockCompraRepo = Mockito.mock(CompraRepository.class);
        mockUsuarioRepo = Mockito.mock(UsuarioRepository.class);
        mockProductoRepo = Mockito.mock(ProductoRepository.class);

        service = new CompraServiceImpl(mockCompraRepo, mockUsuarioRepo, mockProductoRepo);
    }

    @Test
    void realizarCompra_deberiaGuardarYRetornarCompra() {
        // Crear DTO con lista de items
        ItemCompraDTO item = new ItemCompraDTO();
        item.setProductoId(2L);
        item.setCantidad(3);

        CompraDTO dto = new CompraDTO();
        dto.setClienteId(1L);
        dto.setItems(Collections.singletonList(item));

        // Simular cliente
        Usuario cliente = new Usuario();
        cliente.setId(1L);
        cliente.setNombre("Juan");

        // Simular producto
        Producto producto = new Producto("Café Oscuro", "Café intenso", 48000, 10);
        producto.setId(2L);

        // Simular compra guardada
        Compra compraGuardada = new Compra(cliente);
        compraGuardada.setId(1L);
        compraGuardada.setTotal(144000.0);

        // Mockeamos comportamientos
        Mockito.when(mockUsuarioRepo.findById(1L)).thenReturn(Optional.of(cliente));
        Mockito.when(mockProductoRepo.findById(2L)).thenReturn(Optional.of(producto));
        Mockito.when(mockCompraRepo.save(any(Compra.class))).thenReturn(compraGuardada);

        // Ejecutamos el método
        Compra resultado = service.realizarCompra(dto);

        // Validamos
        assertEquals("Juan", resultado.getCliente().getNombre());
        assertEquals(144000.0, resultado.getTotal());
        Mockito.verify(mockCompraRepo, Mockito.times(1)).save(any(Compra.class));
    }
}
