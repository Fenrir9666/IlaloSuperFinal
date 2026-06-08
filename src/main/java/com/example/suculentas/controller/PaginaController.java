package com.example.suculentas.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.suculentas.model.Usuario;

@Controller
public class PaginaController {

    @GetMapping("/cuidados")
    public String cuidados(@AuthenticationPrincipal Usuario usuario, Model model) {
        model.addAttribute("usuario", usuario);
        return "cuidados";
    }

    @GetMapping("/nosotros")
    public String nosotros(@AuthenticationPrincipal Usuario usuario, Model model) {
        model.addAttribute("usuario", usuario);
        return "nosotros";
    }

   
}
