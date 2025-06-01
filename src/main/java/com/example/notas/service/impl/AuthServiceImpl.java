package com.example.notas.service.impl;

import com.example.notas.dto.LoginDTO;
import com.example.notas.model.Estudiantes;
import com.example.notas.model.Profesores;
import com.example.notas.repository.EstudiantesRepository;
import com.example.notas.repository.ProfesoresRepository;
import com.example.notas.security.JwtUtil;
import com.example.notas.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service("AuthService")
public class AuthServiceImpl implements AuthService {

    @Autowired
    private EstudiantesRepository estudiantesRepository;

    @Autowired
    private ProfesoresRepository profesoresRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> login(LoginDTO loginDTO) {
        // Buscar en estudiantes
        Optional<Estudiantes> estudianteOpt = estudiantesRepository.findByEmail(loginDTO.getEmail());
        if (estudianteOpt.isPresent()) {
            Estudiantes estudiante = estudianteOpt.get();
            if (passwordEncoder.matches(loginDTO.getPassword(), estudiante.getPassword())) {
                String token = jwtUtil.generateToken(estudiante.getEmail(), "ESTUDIANTE");
                return generarRespuestaToken(token, estudiante.getEmail(), "ESTUDIANTE");
            }
        }

        // Buscar en profesores
        Optional<Profesores> profesorOpt = profesoresRepository.findByEmail(loginDTO.getEmail());
        if (profesorOpt.isPresent()) {
            Profesores profesor = profesorOpt.get();
            if (passwordEncoder.matches(loginDTO.getPassword(), profesor.getPassword())) {
                String token = jwtUtil.generateToken(profesor.getEmail(), "PROFESOR");
                return generarRespuestaToken(token, profesor.getEmail(), "PROFESOR");
            }
        }

        // Si no coincide ninguno
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
    }

    private Map<String, String> generarRespuestaToken(String token, String email, String rol) {
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("email", email);
        response.put("rol", rol);
        return response;
    }
}

