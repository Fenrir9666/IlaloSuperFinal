package com.example.suculentas.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 1. Captura cuando buscan una página o recurso estático que NO existe (Error 404)
    @ExceptionHandler(NoResourceFoundException.class)
    public String manejarRutaNoEncontrada(Model model) {
        model.addAttribute("titulo", "Página no encontrada");
        model.addAttribute("mensaje", "La suculenta o sección que buscas cambió de lugar o no existe.");
        return "error-personalizado"; // Redirige a error-personalizado.html
    }

    // 2. Captura cualquier otro error interno del servidor (Error 500)
    @ExceptionHandler(Exception.class)
    public String manejarErroresInternos(Exception ex, Model model) {
        model.addAttribute("titulo", "¡Ups! Algo salió mal");
        model.addAttribute("mensaje", "Tuvimos un pequeño problema técnico en nuestro invernadero. Inténtalo de nuevo más tarde.");
        return "error-personalizado";
    }
}
