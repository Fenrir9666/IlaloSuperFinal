package com.example.suculentas.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.suculentas.model.Rol;
import com.example.suculentas.model.Usuario;
import com.example.suculentas.repository.RolRepository;
import com.example.suculentas.repository.UsuarioRepository;

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
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(@ModelAttribute Usuario usuario) {
        // Verificar si ya existe el email
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return "redirect:/registro?error=email";
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
