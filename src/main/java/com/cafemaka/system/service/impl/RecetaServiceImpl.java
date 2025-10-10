package com.cafemaka.system.service.impl;

import com.cafemaka.system.model.Receta;
import org.springframework.stereotype.Service;
import com.cafemaka.system.repository.RecetaRepository;
import com.cafemaka.system.service.interfaces.RecetaService;

import java.util.List;

@Service
public class RecetaServiceImpl implements RecetaService {

    private final RecetaRepository repo;

    public RecetaServiceImpl(RecetaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Receta> listarRecetas() {
        return repo.findAll();
    }

    @Override
    public Receta obtenerPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Receta no encontrada"));
    }

    @Override
    public Receta crear(Receta r) {
        return repo.save(r);
    }

    @Override
    public Receta actualizar(Long id, Receta r) {
        Receta e = repo.findById(id).orElseThrow(() -> new RuntimeException("Receta no encontrada"));
        e.setTitulo(r.getTitulo());
        e.setDescripcion(r.getDescripcion());
        e.setIngredientes(r.getIngredientes());
        e.setPasos(r.getPasos());
        return repo.save(e);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
