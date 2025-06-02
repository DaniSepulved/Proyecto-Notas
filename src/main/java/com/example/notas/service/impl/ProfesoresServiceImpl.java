package com.example.notas.service.impl;

import com.example.notas.dto.ProfesoresDTO;
import com.example.notas.model.Profesores;
import com.example.notas.model.Rol;
import com.example.notas.repository.ProfesoresRepository;
import com.example.notas.service.ProfesoresService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class ProfesoresServiceImpl implements ProfesoresService {

    private final ProfesoresRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ProfesoresServiceImpl(ProfesoresRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Profesores crear(ProfesoresDTO dto) {
        Profesores profesor = Profesores.builder()
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .rol(Rol.PROFESOR) // Asegúrate de establecer el rol
                .build();
        return repository.save(profesor);
    }

    @Override
    public List<Profesores> listar() {
        return repository.findAll();
    }

    @Override
    public Profesores buscarPorId(String id) { // Cambio de Integer a String
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesor no encontrado con id: " + id));
    }

    @Override
    public Profesores actualizar(String id, ProfesoresDTO dto) { // Cambio de Integer a String
        Profesores profesor = buscarPorId(id);
        profesor.setNombre(dto.getNombre());
        profesor.setEmail(dto.getEmail());
        profesor.setPassword(passwordEncoder.encode(dto.getPassword()));
        return repository.save(profesor);
    }

    @Override
    public void eliminar(String id) { // Cambio de Integer a String
        Profesores profesor = buscarPorId(id);
        repository.delete(profesor);
    }
}
