package com.example.suculentas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.suculentas.model.Carrito;
import com.example.suculentas.model.Usuario;
import com.example.suculentas.service.CarritoService;



@Controller
@RequestMapping("/carrito")
public class CarritoController {
    @Autowired private CarritoService carritoService;

    @GetMapping
    public String verCarrito(Model model, @AuthenticationPrincipal Usuario usuario) {
        Carrito carrito = carritoService.obtenerCarritoActivo(usuario);
        model.addAttribute("carrito", carrito);
        model.addAttribute("detalles", carritoService.obtenerDetalles(carrito));
        return "carrito";
    }

       @PostMapping("/agregar/{productoId}")
    public String agregar(@PathVariable Long productoId, 
                          @RequestParam("cantidad") int cantidad,
                          @AuthenticationPrincipal Usuario usuario) {
        
        // 🔹 Si la sesión se llega a perder o expirar, redirigimos al login de inmediato
        if (usuario == null) {
            return "redirect:/login";
        }

        Carrito carrito = carritoService.obtenerCarritoActivo(usuario);
        carritoService.agregarProducto(carrito, productoId, cantidad);
        return "redirect:/carrito";
    }




    @PostMapping("/eliminar/{detalleId}")
    public String eliminar(@PathVariable Long detalleId) {
        carritoService.eliminarProducto(detalleId);
        return "redirect:/carrito";
    }

    @PostMapping("/vaciar")
    public String vaciar(@AuthenticationPrincipal Usuario usuario) {
        Carrito carrito = carritoService.obtenerCarritoActivo(usuario);
        carritoService.vaciarCarrito(carrito);
        return "redirect:/carrito";
    }
}
