package com.ejemplo.demo.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import com.ejemplo.demo.main.model.Producto;
import com.ejemplo.demo.main.repositorio.ProductoRepository;


@Service
public class ProductoService {
    @Autowired
    private ProductoRepository repo;

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto obtenerPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }
    public List<Producto> buscarPorNombre(String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre);
    }
}