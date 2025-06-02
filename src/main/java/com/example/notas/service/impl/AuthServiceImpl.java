package com.example.notas.service.impl;

import com.example.notas.dto.LoginDTO;
import com.example.notas.model.Estudiantes;
import com.example.notas.model.Profesores;
import com.example.notas.model.Rol;
import com.example.notas.repository.EstudiantesRepository;
import com.example.notas.repository.ProfesoresRepository;
import com.example.notas.security.JwtUtil;
import com.example.notas.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service("authService") // Cambiado a minúsculas para seguir convenciones
public class AuthServiceImpl implements AuthService {

    private final EstudiantesRepository estudiantesRepository;
    private final ProfesoresRepository profesoresRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(EstudiantesRepository estudiantesRepository,
                         ProfesoresRepository profesoresRepository,
                         JwtUtil jwtUtil,
                         PasswordEncoder passwordEncoder) {
        this.estudiantesRepository = estudiantesRepository;
        this.profesoresRepository = profesoresRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Map<String, String> login(LoginDTO loginDTO) {
        // Primero verificar si es admin
        if ("admin@admin.com".equals(loginDTO.getEmail())) {
            Optional<Profesores> adminOpt = profesoresRepository.findByEmail(loginDTO.getEmail());
            if (adminOpt.isPresent() && passwordEncoder.matches(loginDTO.getPassword(), adminOpt.get().getPassword())) {
                Profesores admin = adminOpt.get();
                if (admin.getRol() == Rol.ADMIN) {
                    String token = jwtUtil.generateToken(admin.getEmail(), admin.getRol().name());
                    return generarRespuestaToken(token, admin.getEmail(), admin.getRol().name());
                }
            }
        }

        // Buscar en estudiantes
        Optional<Estudiantes> estudianteOpt = estudiantesRepository.findByEmail(loginDTO.getEmail());
        if (estudianteOpt.isPresent()) {
            Estudiantes estudiante = estudianteOpt.get();
            if (passwordEncoder.matches(loginDTO.getPassword(), estudiante.getPassword())) {
                String token = jwtUtil.generateToken(estudiante.getEmail(), estudiante.getRol().name());
                return generarRespuestaToken(token, estudiante.getEmail(), estudiante.getRol().name());
            }
        }

        // Buscar en profesores
        Optional<Profesores> profesorOpt = profesoresRepository.findByEmail(loginDTO.getEmail());
        if (profesorOpt.isPresent()) {
            Profesores profesor = profesorOpt.get();
            if (passwordEncoder.matches(loginDTO.getPassword(), profesor.getPassword())) {
                String token = jwtUtil.generateToken(profesor.getEmail(), profesor.getRol().name());
                return generarRespuestaToken(token, profesor.getEmail(), profesor.getRol().name());
            }
        }

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