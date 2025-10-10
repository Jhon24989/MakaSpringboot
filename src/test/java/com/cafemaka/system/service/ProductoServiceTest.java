package com.cafemaka.system.service;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.cafemaka.system.model.Producto;
import com.cafemaka.system.repository.ProductoRepository;
import com.cafemaka.system.service.impl.ProductoServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceTest {

    private ProductoRepository mockRepo;
    private ProductoServiceImpl service;

    @BeforeEach
    void setup() {
        mockRepo = Mockito.mock(ProductoRepository.class);
        service = new ProductoServiceImpl(mockRepo);
    }

    @Test
    void listar_deberiaRetornarListaDeProductos() {
        Producto p1 = new Producto("Café Premium", "Grano fuerte", 45000, 10);
        Producto p2 = new Producto("Café Suave", "Café molido", 35000, 20);

        Mockito.when(mockRepo.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Producto> resultado = service.listar();

        assertEquals(2, resultado.size());
        assertEquals("Café Premium", resultado.get(0).getNombre());
        Mockito.verify(mockRepo, Mockito.times(1)).findAll();
    }

    @Test
    void guardar_deberiaRetornarProductoGuardado() {
        Producto p = new Producto("Café Oscuro", "Café intenso", 48000, 5);
        Producto guardado = new Producto("Café Oscuro", "Café intenso", 48000, 5);

        Mockito.when(mockRepo.save(p)).thenReturn(guardado);

        Producto resultado = service.guardar(p);

        assertEquals("Café Oscuro", resultado.getNombre());
        assertEquals("Café intenso", resultado.getDescripcion());
        assertEquals(48000, resultado.getPrecio());
        assertEquals(5, resultado.getStock());
        Mockito.verify(mockRepo, Mockito.times(1)).save(p);
    }
}
