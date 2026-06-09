
package com.example.suculentas.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==========================
    // Nombre
    // ==========================
    @Column(nullable = false)
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 2, max = 50,
            message = "El nombre debe tener entre 2 y 50 caracteres.")
    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?: [A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$",
            message = "El nombre solo puede contener letras.")
    private String nombre;

    // ==========================
    // Apellido
    // ==========================
    @Column(nullable = false)
    @NotBlank(message = "El apellido es obligatorio.")
    @Size(min = 2, max = 50,
            message = "El apellido debe tener entre 2 y 50 caracteres.")
    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?: [A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$",
            message = "El apellido solo puede contener letras.")
    private String apellido;

    // ==========================
    // Email
    // ==========================
    @Column(nullable = false, unique = true)
    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Email(message = "Por favor, introduce un correo electrónico válido.")
    private String email;

    // ==========================
    // Contraseña
    // ==========================
    @Column(nullable = false)
    @NotBlank(message = "La contraseña es obligatoria.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "La contraseña debe tener mínimo 8 caracteres, una mayúscula, una minúscula y un número.")
    private String password;

    // ==========================
    // Teléfono
    // ==========================
    @NotBlank(message = "El teléfono es obligatorio.")
    @Pattern(
            regexp = "^09\\d{8}$",
            message = "Ingrese un número de celular válido.")
    private String telefono;

    // ==========================
    // Dirección
    // ==========================
    @NotBlank(message = "La dirección es obligatoria.")
    @Size(min = 5, max = 100,
            message = "La dirección debe tener entre 5 y 100 caracteres.")
    private String direccion;

    // ==========================
    // Otros campos
    // ==========================
    @Column(nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @Column(nullable = false)
    private boolean activo = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos = new ArrayList<>();

    public Usuario() {
    }

    // ==========================
    // Spring Security
    // ==========================
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (rol != null && rol.getNombre() != null) {
            return List.of(
                    new SimpleGrantedAuthority(
                            "ROLE_" + rol.getNombre().toUpperCase()));
        }

        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return activo;
    }

    // ==========================
    // Getters y Setters
    // ==========================
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}

