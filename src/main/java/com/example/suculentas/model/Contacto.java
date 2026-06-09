package com.example.suculentas.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email; // 👈 Importación para validar correos
import jakarta.validation.constraints.NotBlank; // 👈 Importación para evitar textos vacíos
import jakarta.validation.constraints.Size; // 👈 Importación para controlar tamaños

@Entity
@Table(name = "contacto") 
public class Contacto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Candado para el Nombre
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres.")
    private String nombre;

    // Candado para el Email
    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Email(message = "Por favor, introduce un correo electrónico válido.")
    private String email;

    // Candado para el Asunto
    @NotBlank(message = "El asunto es obligatorio.")
    @Size(min = 5, max = 100, message = "El asunto debe tener al menos 5 caracteres.")
    private String asunto;

    // Candado para el Mensaje
    @NotBlank(message = "El mensaje no puede estar vacío.")
    @Size(min = 10, max = 500, message = "El mensaje debe tener entre 10 y 500 caracteres.")
    private String mensaje;

    private LocalDateTime fechaEnvio = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // 🔹 Getters y Setters (Se mantienen exactamente iguales a los tuyos)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
