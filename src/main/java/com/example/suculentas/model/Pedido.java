package com.example.suculentas.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    @Column(nullable = false)
    private double total;

    @Column(nullable = false)
    private String estado;

    // MUCHOS pedidos -> UN usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) 
    private Usuario usuario;

    // UN pedido -> MUCHOS detalles
     @OneToMany(
        mappedBy = "pedido",              //  Indica que la relación está definida en la clase DetallePedido
                                          //    (campo 'pedido' con @ManyToOne). Es decir, la FK está en la tabla detalle_pedido.
        cascade = CascadeType.ALL,        //  Todas las operaciones sobre Pedido se propagan a sus DetallePedido:
                                          //    - persist (guardar)
                                          //    - merge (actualizar)
                                          //    - remove (eliminar)
                                          //    - refresh, detach
        orphanRemoval = true              //  Si eliminas un DetallePedido de la lista 'detalles',
                                          //    Hibernate lo borra automáticamente de la base de datos.
    )
    private List<DetallePedido> detalles = new ArrayList<>();

    public Pedido() {
    }

    // GETTERS Y SETTERS
    public Long getId() {
        return id;
    }

    // AGREGADO: Setter para el ID por consistencia en JPA
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }
}
