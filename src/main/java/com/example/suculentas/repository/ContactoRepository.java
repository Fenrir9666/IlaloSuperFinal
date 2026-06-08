package com.example.suculentas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.suculentas.model.Contacto;
import com.example.suculentas.model.Usuario;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {

    // Buscar mensajes por email exacto
    List<Contacto> findByEmail(String email);

    // Buscar mensajes por nombre (contiene texto)
    List<Contacto> findByNombreContainingIgnoreCase(String nombre);

    // Buscar mensajes por asunto
    List<Contacto> findByAsuntoContainingIgnoreCase(String asunto);

    // Buscar mensajes enviados por un usuario específico
    List<Contacto> findByUsuario(Usuario usuario);

    // Buscar mensajes enviados después de una fecha
    List<Contacto> findByFechaEnvioAfter(java.time.LocalDateTime fecha);

    // Buscar mensajes enviados antes de una fecha
    List<Contacto> findByFechaEnvioBefore(java.time.LocalDateTime fecha);
}
