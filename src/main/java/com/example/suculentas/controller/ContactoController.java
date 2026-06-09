package com.example.suculentas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.suculentas.model.Contacto;
import com.example.suculentas.repository.ContactoRepository; // ✅ Nombre de importación corregido

@Controller
public class ContactoController {

    @Autowired
    private ContactoRepository contactoRepository; // ✅ Nombre de la variable corregido con la R correcta

    // Mostrar formulario vacío
    @GetMapping("/contacto")
    public String mostrarFormulario(Model model) {
        if (!model.containsAttribute("contacto")) {
            model.addAttribute("contacto", new Contacto());
        }
        return "contacto"; 
    }

    // Guardar mensaje en la base de datos de forma segura
    @PostMapping("/contacto")
    public String enviarMensaje(@ModelAttribute Contacto contacto, RedirectAttributes redirectAttributes) {
        contactoRepository.save(contacto);
        
        // 🌟 Aquí definimos el texto premium que saldrá volando en la alerta
        redirectAttributes.addFlashAttribute("mensajeExito", "✨ ¡Tu mensaje ha sido enviado con éxito!");
        
        return "redirect:/contacto"; 
    }

}
