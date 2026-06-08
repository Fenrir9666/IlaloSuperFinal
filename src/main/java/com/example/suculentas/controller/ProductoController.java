package com.example.suculentas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.suculentas.model.Usuario;
import com.example.suculentas.repository.ProductoRepository;

@Controller
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // Pantalla de Inicio (Usa Spring Security nativo)
    @GetMapping
    public String index(Model model, @AuthenticationPrincipal Usuario usuario) {
        
        System.out.println("🏠 INDEX - Usuario en sesión: " + 
            (usuario != null ? usuario.getNombre() : "NULL"));
        
        if (usuario != null) {
            model.addAttribute("usuarioLogueado", usuario);
        }
        
        model.addAttribute("productos", productoRepository.findAll());
        return "index";
    }

    // Ruta unificada para el Catálogo (Soporta ver todo o filtrar por categoría)
    @GetMapping("/catalogo")
    public String verCatalogo(@RequestParam(name = "categoria", required = false) String categoria, Model model) {
        
        // Si no hay filtro, muestra absolutamente todo lo que esté en la base de datos
        if (categoria != null && !categoria.isEmpty()) {
            model.addAttribute("productos", productoRepository.findByCategoria_NombreIgnoreCase(categoria));
        } else {
            model.addAttribute("productos", productoRepository.findAll());
        }
        return "catalogo";
    }
}
