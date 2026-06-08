package com.example.suculentas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.suculentas.model.Carrito;
import com.example.suculentas.model.Usuario;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    // Buscar el carrito activo del usuario
    Optional<Carrito> findByUsuarioAndActivoTrue(Usuario usuario);

    // Buscar los carritos por el email del usuario
    List<Carrito> findByUsuarioEmail(String email);
}
