package com.example.suculentas.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    // UNA categoría -> MUCHOS productos
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos = new ArrayList<>();

    // CONSTRUCTORES
    public Categoria() {
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    // GETTERS Y SETTERS
    public Long getId() {
        return id;
    }

    // AGREGADO: Setter para el ID por consistencia en JPA
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
