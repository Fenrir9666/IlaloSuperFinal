package com.example.suculentas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.suculentas.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    // Buscar rol por nombre exacto (ej: "ROLE_ADMIN", "ROLE_USER")
    Optional<Rol> findByNombre(String nombre);

    // Buscar roles cuyo nombre contenga texto (ignora mayúsculas/minúsculas)
    List<Rol> findByNombreContainingIgnoreCase(String texto);

    // Verificar si existe un rol con un nombre específico
    boolean existsByNombre(String nombre);
}
