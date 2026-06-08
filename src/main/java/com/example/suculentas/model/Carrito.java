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
@Table(name = "carritos")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //* parémtros de la tabla carrito */

    @ManyToOne //* de muchos a uno siempre uso una conexion simple */
    @JoinColumn(name = "usuario_id", nullable = false) //!conectamos con la tabla 
    private Usuario usuario;                          //! de usuario   

    @Column(nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now(); //* parémtros de la tabla carrito */

    @Column(nullable = false)
    private boolean activo = true; //* parémtros de la tabla carrito */

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarritoDetalle> detalles = new ArrayList<>(); //! conectamos con la tabla
                                                            //! CarritoDetalle
    
    //* si en cambio la relacion es de uno a muchos, siemore se usa list, set o collection */
    
    //! CONSTRUCTOR VACÍO
    public Carrito() {
    }

// Dentro de la clase Carrito.java, añade:
public Carrito(Usuario usuario) {
    this.usuario = usuario;
    this.fechaCreacion = java.time.LocalDateTime.now();
    this.activo = true;
}



    // CONSTRUCTOR CON PARÁMETROS
    public Carrito(Usuario usuario, LocalDateTime fechaCreacion, boolean activo) {
        this.usuario = usuario;
        this.fechaCreacion = fechaCreacion;
        this.activo = activo;
    }


    // GETTERS Y SETTERS
    public Long getId() {
        return id;
    }

    // AGREGADO: Setter para el ID por consistencia en JPA
    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<CarritoDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<CarritoDetalle> detalles) {
        this.detalles = detalles;
    }
}
