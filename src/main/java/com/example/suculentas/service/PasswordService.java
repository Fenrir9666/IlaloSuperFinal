package com.example.suculentas.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Encripta la contraseña antes de guardarla en la base de datos
    public String encriptar(String password) {
        return encoder.encode(password);
    }

    // Verifica si la contraseña ingresada coincide con la encriptada
    public boolean verificar(String passwordPlano, String passwordEncriptado) {
        return encoder.matches(passwordPlano, passwordEncriptado);
    }
}
