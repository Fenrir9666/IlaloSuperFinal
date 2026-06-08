package com.example.suculentas.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.suculentas.model.Pedido;
import com.example.suculentas.model.Usuario;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Buscar pedidos por usuario
    List<Pedido> findByUsuario(Usuario usuario);

    // Buscar pedidos por estado (ej: "Pendiente", "Pagado", "Cancelado")
    List<Pedido> findByEstado(String estado);

    // Buscar pedidos con total mayor a un valor
    List<Pedido> findByTotalGreaterThan(double total);

    // Buscar pedidos con total menor a un valor
    List<Pedido> findByTotalLessThan(double total);

    // Buscar pedidos creados después de una fecha
    List<Pedido> findByFechaAfter(LocalDateTime fecha);

    // Buscar pedidos creados antes de una fecha
    List<Pedido> findByFechaBefore(LocalDateTime fecha);

    // Buscar pedidos por usuario y estado
    List<Pedido> findByUsuarioAndEstado(Usuario usuario, String estado);
}
