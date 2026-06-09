package com.example.suculentas.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // ✅ Importación para activar las reglas del modelo
import org.springframework.stereotype.Controller; // ✅ Importación para atrapar los errores
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.suculentas.model.Rol;
import com.example.suculentas.model.Usuario;
import com.example.suculentas.repository.RolRepository;
import com.example.suculentas.repository.UsuarioRepository;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository usuarioRepository,
                          RolRepository rolRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        if (!model.containsAttribute("usuario")) {
            model.addAttribute("usuario", new Usuario());
        }
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(@Valid @ModelAttribute("usuario") Usuario usuario, 
                            BindingResult bindingResult, 
                            Model model) {
        
        // 🚨 1. SI HAY ERRORES DE CAMPOS VACÍOS O FORMATOS MAL ESCRITOS
        if (bindingResult.hasErrors()) {
            model.addAttribute("usuario", usuario);
            return "registro"; // Regresa al formulario sin borrar lo escrito para que corrija
        }

        // 🚨 2. VERIFICAR SI YA EXISTE EL EMAIL EN LA BD
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            model.addAttribute("errorEmail", "Este correo electrónico ya está registrado.");
            model.addAttribute("usuario", usuario);
            return "registro";
        }

        // Asignar rol por defecto
        Rol rolUsuario = rolRepository.findByNombre("USER")
                .orElseGet(() -> rolRepository.save(new Rol("USER", "Rol de usuario estándar")));
        usuario.setRol(rolUsuario);

        // Encriptar contraseña antes de guardar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        usuarioRepository.save(usuario);

        // Redirigir al login con mensaje de éxito
        return "redirect:/login?success";
    }
}
