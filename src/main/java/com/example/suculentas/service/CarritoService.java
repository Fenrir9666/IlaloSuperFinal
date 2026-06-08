package com.example.suculentas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.suculentas.model.Carrito;
import com.example.suculentas.model.CarritoDetalle;
import com.example.suculentas.model.Producto;
import com.example.suculentas.model.Usuario;
import com.example.suculentas.repository.CarritoDetalleRepository;
import com.example.suculentas.repository.CarritoRepository;
import com.example.suculentas.repository.ProductoRepository;

@Service
public class CarritoService {

    @Autowired private CarritoRepository carritoRepo;
    @Autowired private CarritoDetalleRepository detalleRepo;
    @Autowired private ProductoRepository productoRepo;

    public Carrito obtenerCarritoActivo(Usuario usuario) {
        return carritoRepo.findByUsuarioAndActivoTrue(usuario)
                          .stream().findFirst()
                          .orElseGet(() -> crearCarrito(usuario));
    }

    private Carrito crearCarrito(Usuario usuario) {
        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);
        carrito.setActivo(true);
        carrito.setFechaCreacion(LocalDateTime.now());
        return carritoRepo.save(carrito);
    }

    public void agregarProducto(Carrito carrito, Long productoId, int cantidad) {
        Producto producto = productoRepo.findById(productoId).orElseThrow();
        CarritoDetalle existente = detalleRepo.findByCarritoAndProducto(carrito, producto);

        if (existente != null) {
            existente.setCantidad(existente.getCantidad() + cantidad);
            detalleRepo.save(existente);
        } else {
            CarritoDetalle detalle = new CarritoDetalle(carrito, producto, cantidad, producto.getPrecio());
            detalleRepo.save(detalle);
        }
    }

    public void eliminarProducto(Long detalleId) {
        detalleRepo.deleteById(detalleId);
    }

    // ✅ Método corregido para vaciar carrito
    @Transactional
    public void vaciarCarrito(Carrito carrito) {
        detalleRepo.deleteAllByCarrito(carrito);
    }

    @Transactional(readOnly = true)
    public List<CarritoDetalle> obtenerDetalles(Carrito carrito) {
        return detalleRepo.findByCarrito(carrito);
    }

    public int contarProductos(String username) {
        List<Carrito> carritos = carritoRepo.findByUsuarioEmail(username);

        return carritos.stream()
                .findFirst()
                .map(carrito -> detalleRepo.countByCarrito(carrito))
                .orElse(0);
    }
}
