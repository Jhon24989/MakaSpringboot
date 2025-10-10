package com.cafemaka.system.controller;

import com.cafemaka.system.model.Producto;
import com.cafemaka.system.service.interfaces.ProductoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductoControllerTest {

    @Test
    void listar_deberiaRetornarOkYLista() {
        ProductoService mockService = Mockito.mock(ProductoService.class);

        Producto p1 = new Producto("Café Premium", "Grano fuerte", 45000, 10);
        Producto p2 = new Producto("Café Suave", "Café molido", 35000, 20);

        Mockito.when(mockService.listar()).thenReturn(Arrays.asList(p1, p2));

        ProductoController controller = new ProductoController(mockService, null);
        ResponseEntity<List<Producto>> respuesta = controller.listar();

        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(2, respuesta.getBody().size());
        assertEquals("Café Premium", respuesta.getBody().get(0).getNombre());
    }
}

