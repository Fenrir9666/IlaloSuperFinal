package com.example.suculentas.controller; // Asegúrate que este paquete sea el correcto

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.suculentas.service.CarritoService;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private CarritoService carritoService;

    @ModelAttribute("cantidadProductos")
    public int getCantidadProductos(Authentication auth) {
        // Esto verifica que el usuario haya iniciado sesión
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            return carritoService.contarProductos(auth.getName());
        }
        return 0;
    }
}