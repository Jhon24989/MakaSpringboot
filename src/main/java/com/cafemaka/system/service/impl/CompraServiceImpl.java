package com.cafemaka.system.service.impl;

import jakarta.transaction.Transactional;
import com.cafemaka.system.model.Compra;
import com.cafemaka.system.model.DetalleCompra;
import com.cafemaka.system.model.Producto;
import com.cafemaka.system.model.Usuario;
import com.cafemaka.system.model.dto.CompraDTO;
import com.cafemaka.system.model.dto.ItemCompraDTO;
import org.springframework.stereotype.Service;
import com.cafemaka.system.repository.CompraRepository;
import com.cafemaka.system.repository.ProductoRepository;
import com.cafemaka.system.repository.UsuarioRepository;
import com.cafemaka.system.service.interfaces.CompraService;

import java.util.List;

@Service
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepo;
    private final UsuarioRepository usuarioRepo;
    private final ProductoRepository productoRepo;

    public CompraServiceImpl(CompraRepository compraRepo,
                             UsuarioRepository usuarioRepo,
                             ProductoRepository productoRepo) {
        this.compraRepo = compraRepo;
        this.usuarioRepo = usuarioRepo;
        this.productoRepo = productoRepo;
    }

    @Override
    @Transactional // si algo falla revierte los cambios hechos en la database
    public Compra realizarCompra(CompraDTO request) {
        Usuario cliente = usuarioRepo.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Compra compra = new Compra(cliente);
        double total = 0.0;

        for (ItemCompraDTO itemReq : request.getItems()) {
            Producto p = productoRepo.findById(itemReq.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + itemReq.getProductoId()));

            if (p.getStock() < itemReq.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para producto: " + p.getNombre());
            }

            double subtotal = p.getPrecio() * itemReq.getCantidad();
            DetalleCompra detalle = new DetalleCompra(p, itemReq.getCantidad(), subtotal);
            detalle.setCompra(compra);
            compra.getDetalles().add(detalle);

            // descontar stock
            p.setStock(p.getStock() - itemReq.getCantidad());
            productoRepo.save(p);

            total += subtotal;
        }

        compra.setTotal(total);
        return compraRepo.save(compra);
    }

    @Override
    public List<Compra> obtenerHistorial(Long clienteId) {
        return compraRepo.findByClienteId(clienteId);
    }

}
