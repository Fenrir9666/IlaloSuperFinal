package com.example.suculentas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.suculentas.model.Contacto;
import com.example.suculentas.repository.ContactoRepository;

@Controller
public class ContactoController {

    @Autowired
    private ContactoRepository contactoRepository;

    // Mostrar formulario vacío
    @GetMapping("/contacto")
    public String mostrarFormulario(Model model) {
        model.addAttribute("contacto", new Contacto());
        return "contacto"; // Renderiza contacto.html
    }

    // Guardar mensaje en la base de datos
    @PostMapping("/contacto")
    public String enviarMensaje(@ModelAttribute Contacto contacto, Model model) {
        contactoRepository.save(contacto);
        model.addAttribute("mensajeExito", "Tu mensaje ha sido enviado correctamente.");
        model.addAttribute("contacto", new Contacto()); // limpia el formulario
        return "contacto"; // Vuelve a la misma vista
    }
}
