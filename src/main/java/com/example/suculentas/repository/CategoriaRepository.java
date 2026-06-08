package com.example.suculentas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.suculentas.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Buscar categoría por nombre exacto
    Optional<Categoria> findByNombre(String nombre);

    // Buscar categoría ignorando mayúsculas/minúsculas
    Optional<Categoria> findByNombreIgnoreCase(String nombre);

    // Buscar categorías cuyo nombre contenga un texto
    List<Categoria> findByNombreContainingIgnoreCase(String texto);
}
