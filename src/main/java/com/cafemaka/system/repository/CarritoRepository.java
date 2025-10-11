package com.cafemaka.system.repository;

import com.cafemaka.system.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CarritoRepository extends JpaRepository<Carrito, Long> {
        Optional<Carrito> findByUsuarioId(Long usuarioId);
}




