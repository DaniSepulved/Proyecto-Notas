package com.example.notas.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.notas.model.Estudiantes;
import com.example.notas.model.Profesores;
import com.example.notas.repository.EstudiantesRepository;
import com.example.notas.repository.ProfesoresRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final EstudiantesRepository estudiantesRepository;
    private final ProfesoresRepository profesoresRepository;

    public JwtFilter(JwtUtil jwtUtil, 
                    EstudiantesRepository estudiantesRepository, 
                    ProfesoresRepository profesoresRepository) {
        this.jwtUtil = jwtUtil;
        this.estudiantesRepository = estudiantesRepository;
        this.profesoresRepository = profesoresRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                if (jwtUtil.validateToken(token)) {
                    String email = jwtUtil.getEmailFromToken(token);
                    String role = jwtUtil.getRoleFromToken(token);

                    Object principal = null;
                    String userRole = null;

                    if ("ESTUDIANTE".equals(role)) {
                        Optional<Estudiantes> optionalEstudiante = estudiantesRepository.findByEmail(email);
                        if (optionalEstudiante.isPresent()) {
                            Estudiantes estudiante = optionalEstudiante.get();
                            principal = estudiante;
                            userRole = "ESTUDIANTE";
                        }
                    } else if ("PROFESOR".equals(role)) {
                        Optional<Profesores> optionalProfesor = profesoresRepository.findByEmail(email);
                        if (optionalProfesor.isPresent()) {
                            Profesores profesor = optionalProfesor.get();
                            principal = profesor;
                            userRole = "PROFESOR";
                        }
                    } else if ("ADMIN".equals(role)) {
                        principal = email;
                        userRole = "ADMIN";
                    }

                    if (principal != null && userRole != null) {
                        UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                principal,
                                null,
                                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userRole))
                            );
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (Exception e) {
                logger.error("Error procesando el token JWT", e);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido o expirado");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}