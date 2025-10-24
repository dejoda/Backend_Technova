package com.ejemplo.demo.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ejemplo.demo.main.model.Producto;
import com.ejemplo.demo.main.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService service;

    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }
    @GetMapping("/buscar")
    public List<Producto> buscarPorNombre(@RequestParam String nombre) {
        return service.buscarPorNombre(nombre);
    }
}