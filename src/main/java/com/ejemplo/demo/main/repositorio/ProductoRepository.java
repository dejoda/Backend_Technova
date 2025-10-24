package com.ejemplo.demo.main.repositorio;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ejemplo.demo.main.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}