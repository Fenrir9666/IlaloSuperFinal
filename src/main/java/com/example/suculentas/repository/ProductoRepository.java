package com.example.suculentas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.suculentas.model.Categoria;
import com.example.suculentas.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Buscar productos por nombre exacto
    List<Producto> findByNombre(String nombre);

    // Buscar productos cuyo nombre contenga texto (ignora mayúsculas/minúsculas)
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    // Buscar productos por categoría
    List<Producto> findByCategoria(Categoria categoria);

    // Buscar productos disponibles
    List<Producto> findByDisponibleTrue();

    // Buscar productos no disponibles
    List<Producto> findByDisponibleFalse();

    // Buscar productos con precio mayor a un valor
    List<Producto> findByPrecioGreaterThan(double precio);

    // Buscar productos con precio menor a un valor
    List<Producto> findByPrecioLessThan(double precio);

    // Buscar productos por tamaño
    List<Producto> findByTamanoIgnoreCase(String tamano);

    // Buscar productos por color
    List<Producto> findByColorIgnoreCase(String color);
    // Busca productos a través del campo 'nombre' de la entidad 'Categoria' ignorando mayúsculas
List<Producto> findByCategoria_NombreIgnoreCase(String nombre);

}
