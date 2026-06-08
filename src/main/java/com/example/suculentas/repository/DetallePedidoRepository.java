package com.example.suculentas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.suculentas.model.DetallePedido;
import com.example.suculentas.model.Pedido;
import com.example.suculentas.model.Producto;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

    // Buscar todos los detalles de un pedido específico
    List<DetallePedido> findByPedido(Pedido pedido);

    // Buscar todos los detalles de un producto específico
    List<DetallePedido> findByProducto(Producto producto);

    // Buscar detalles con cantidad mayor a un valor
    List<DetallePedido> findByCantidadGreaterThan(int cantidad);

    // Buscar detalles con precio unitario menor a un valor
    List<DetallePedido> findByPrecioUnitarioLessThan(double precio);

    // Buscar detalle específico por pedido y producto
    DetallePedido findByPedidoAndProducto(Pedido pedido, Producto producto);
}
