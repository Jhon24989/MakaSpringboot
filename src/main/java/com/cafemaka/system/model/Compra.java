package com.cafemaka.system.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario cliente;

    private double total;
    private LocalDateTime fecha;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleCompra> detalles = new ArrayList<>();

    public Compra() {}

    public Compra(Usuario cliente) {
        this.cliente = cliente;
        this.fecha = LocalDateTime.now();
    }

    // getters/setters
    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getCliente() { return cliente; }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }
    public double getTotal() { return total; }

    public void setTotal(double total) {
        this.total = total;
    }
    public LocalDateTime getFecha() { return fecha; }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public List<DetalleCompra> getDetalles() { return detalles; }

    public void setDetalles(List<DetalleCompra> detalles) {
        this.detalles = detalles;
    }
}
