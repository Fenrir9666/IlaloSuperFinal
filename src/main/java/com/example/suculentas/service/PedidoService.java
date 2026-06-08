package com.example.suculentas.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.suculentas.model.CarritoDetalle;
import com.example.suculentas.model.DetallePedido;
import com.example.suculentas.model.Pedido;
import com.example.suculentas.model.Usuario;
import com.example.suculentas.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // Crear un pedido nuevo a partir de CarritoDetalle
    public Pedido crearPedido(Usuario usuario, List<CarritoDetalle> detallesCarrito) {
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstado("Pendiente");

        // Calcular total
        double total = detallesCarrito.stream()
                .mapToDouble(d -> d.getPrecioUnitario() * d.getCantidad())
                .sum();
        pedido.setTotal(total);

        // Convertir CarritoDetalle → DetallePedido de forma modificable
        List<DetallePedido> detallesPedido = detallesCarrito.stream().map(cd -> {
            DetallePedido dp = new DetallePedido();
            dp.setPedido(pedido);
            dp.setProducto(cd.getProducto());
            dp.setCantidad(cd.getCantidad());
            dp.setPrecioUnitario(cd.getPrecioUnitario());
            return dp;
        }).collect(Collectors.toList()); // ✅ Solución: Lista editable para Hibernate

        // Asegurar que la colección no sea inmutable
        pedido.setDetalles(new ArrayList<>(detallesPedido));

        return pedidoRepository.save(pedido);
    }

    // Confirmar compra (cambiar estado y guardar)
    public Pedido confirmarPedido(Pedido pedido) {
        pedido.setEstado("Procesado");
        return pedidoRepository.save(pedido);
    }

    // Obtener historial de un usuario
    public List<Pedido> obtenerHistorial(Usuario usuario) {
        return pedidoRepository.findByUsuario(usuario);
    }

    // Buscar pedidos pendientes de un usuario
    public List<Pedido> obtenerPendientes(Usuario usuario) {
        return pedidoRepository.findByUsuarioAndEstado(usuario, "Pendiente");
    }

    // Buscar pedido por ID
    public Pedido obtenerPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }
}
