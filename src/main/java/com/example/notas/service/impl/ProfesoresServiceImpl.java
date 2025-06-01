// ProfesoresServiceImpl.java
package com.example.notas.service.impl;

import com.example.notas.dto.ProfesoresDTO;
import com.example.notas.model.Profesores;
import com.example.notas.repository.ProfesoresRepository;
import com.example.notas.service.ProfesoresService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
                .build();
        return repository.save(profesor);
    }

    @Override
    public List<Profesores> listar() {
        return repository.findAll();
    }

    @Override
    public Profesores buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profesor no encontrado con id: " + id));
    }

    @Override
    public Profesores actualizar(Integer id, ProfesoresDTO dto) {
        Profesores profesor = buscarPorId(id);
        profesor.setNombre(dto.getNombre());
        profesor.setEmail(dto.getEmail());
        profesor.setPassword(passwordEncoder.encode(dto.getPassword()));
        return repository.save(profesor);
    }

    @Override
    public void eliminar(Integer id) {
        Profesores profesor = buscarPorId(id);
        repository.delete(profesor);
    }
}
