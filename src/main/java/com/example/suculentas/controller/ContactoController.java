package com.example.suculentas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; // ✅ Usaremos esto para pasar los errores de forma directa
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.suculentas.model.Contacto;
import com.example.suculentas.repository.ContactoRepository;

import jakarta.validation.Valid;

@Controller
public class ContactoController {

    @Autowired
    private ContactoRepository contactoRepository;

    // Mostrar formulario vacío
    @GetMapping("/contacto")
    public String mostrarFormulario(Model model) {
        if (!model.containsAttribute("contacto")) {
            model.addAttribute("contacto", new Contacto());
        }
        return "contacto"; 
    }

    // Guardar mensaje en la base de datos de forma segura y validada
    @PostMapping("/contacto")
    public String enviarMensaje(@Valid @ModelAttribute("contacto") Contacto contacto, 
                                BindingResult bindingResult, 
                                Model model, // ✅ Parámetro obligatorio agregado aquí
                                RedirectAttributes redirectAttributes) {
        
        // 🚨 SI EL CLIENTE DEJÓ CAMPOS VACÍOS O CORREOS MAL ESCRITOS
        if (bindingResult.hasErrors()) {
            // Pasamos los errores y los datos de vuelta al modelo sin refrescar la URL
            model.addAttribute("contacto", contacto);
            
            // 🌟 ¡LA CORRECCIÓN CLAVE! Renderiza la plantilla directamente para que Thymeleaf pinte las letras rojas
            return "contacto"; 
        }

        // Si todos los datos son válidos y pasaron los candados, se guarda en MySQL
        contactoRepository.save(contacto);
        
        // El cartel verde de éxito solo saldrá si el mensaje es real y válido
        redirectAttributes.addFlashAttribute("mensajeExito", "✨ ¡Tu mensaje ha sido enviado con éxito!");
        
        return "redirect:/contacto"; 
    }
}
