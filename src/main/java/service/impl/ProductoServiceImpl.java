package service.impl;

import model.Producto;
import org.springframework.stereotype.Service;
import repository.ProductoRepository;
import service.interfaces.ProductoService;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repo;

    public ProductoServiceImpl(ProductoRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Producto> listar() {
        return repo.findAll();
    }

    @Override
    public Producto guardar(Producto producto) {
        return repo.save(producto);
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        Producto p = repo.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        p.setNombre(producto.getNombre());
        p.setDescripcion(producto.getDescripcion());
        p.setPrecio(producto.getPrecio());
        p.setStock(producto.getStock());
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
