package com.example.suculentas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.suculentas.model.Rol;
import com.example.suculentas.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por email (clave única)
    Optional<Usuario> findByEmail(String email);

    // Verificar si existe un usuario con un email específico
    boolean existsByEmail(String email);

    // Buscar usuarios activos
    List<Usuario> findByActivoTrue();

    // Buscar usuarios inactivos
    List<Usuario> findByActivoFalse();

    // Buscar usuarios por nombre (contiene texto, ignorando mayúsculas/minúsculas)
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);

    // Buscar usuarios por apellido
    List<Usuario> findByApellidoContainingIgnoreCase(String apellido);

    // Buscar usuarios por rol
    List<Usuario> findByRol(Rol rol);

    // Buscar usuarios registrados después de una fecha
    List<Usuario> findByFechaRegistroAfter(java.time.LocalDateTime fecha);

    // Buscar usuarios registrados antes de una fecha
    List<Usuario> findByFechaRegistroBefore(java.time.LocalDateTime fecha);
}
