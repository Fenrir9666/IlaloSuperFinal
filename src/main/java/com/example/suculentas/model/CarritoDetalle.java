package com.example.suculentas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrito_detalle")
public class CarritoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // MUCHOS detalles pertenecen a UN carrito
    @ManyToOne 
    @JoinColumn(name = "carrito_id", nullable = false)
    private Carrito carrito;

    // MUCHOS detalles pueden apuntar a UN producto
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private double precioUnitario;

    // =====================================
    // CONSTRUCTOR VACÍO
    // =====================================
    public CarritoDetalle() {
    }

    // =====================================
    // CONSTRUCTOR CON PARÁMETROS
    // =====================================
    public CarritoDetalle(Carrito carrito, Producto producto,
                           int cantidad, double precioUnitario) {
        this.carrito = carrito;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // =====================================
    // GETTERS Y SETTERS
    // =====================================
    public Long getId() {
        return id;
    }

    // AGREGADO: Setter para el ID por consistencia en JPA
    public void setId(Long id) {
        this.id = id;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
