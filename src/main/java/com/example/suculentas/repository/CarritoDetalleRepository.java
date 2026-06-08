package com.example.suculentas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.suculentas.model.Carrito;
import com.example.suculentas.model.CarritoDetalle;
import com.example.suculentas.model.Producto;

@Repository
public interface CarritoDetalleRepository extends JpaRepository<CarritoDetalle, Long> {

    List<CarritoDetalle> findByCarrito(Carrito carrito);

    List<CarritoDetalle> findByProducto(Producto producto);

    List<CarritoDetalle> findByCantidadGreaterThan(int cantidad);

    List<CarritoDetalle> findByPrecioUnitarioLessThan(double precio);

    CarritoDetalle findByCarritoAndProducto(Carrito carrito, Producto producto);

    int countByCarrito(Carrito carrito);

    void deleteAllByCarrito(Carrito carrito);

    @Modifying
    @Transactional
    @Query("DELETE FROM CarritoDetalle d WHERE d.carrito = :carrito")
    void borrarPorCarrito(Carrito carrito);
}
