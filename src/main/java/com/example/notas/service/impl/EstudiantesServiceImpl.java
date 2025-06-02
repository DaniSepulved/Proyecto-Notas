package com.example.notas.service.impl;

import com.example.notas.dto.EstudiantesDTO;
import com.example.notas.model.Estudiantes;
import com.example.notas.model.Rol;
import com.example.notas.repository.EstudiantesRepository;
import com.example.notas.service.EstudiantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EstudiantesServiceImpl implements EstudiantesService {

    private final EstudiantesRepository repository;

    @Autowired
    public EstudiantesServiceImpl(EstudiantesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Estudiantes crear(EstudiantesDTO dto) {
        Estudiantes estudiante = new Estudiantes();
        estudiante.setNombre(dto.getNombre());
        estudiante.setEmail(dto.getEmail());
        estudiante.setPassword(dto.getPassword());
        estudiante.setRol(Rol.ESTUDIANTE); // o dto.getRol() si viene del DTO
        return repository.save(estudiante);
    }


    @Override
    public List<Estudiantes> listar() {
        return repository.findAll();
    }

    @Override
    public Estudiantes buscarPorId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante no encontrado"));
    }

    @Override
    public Estudiantes actualizar(String id, EstudiantesDTO dto) {
        Estudiantes estudiante = buscarPorId(id);
        estudiante.setNombre(dto.getNombre());
        estudiante.setEmail(dto.getEmail());
        estudiante.setPassword(dto.getPassword());
        return repository.save(estudiante);
    }

    @Override
    public void eliminar(String id) {
        Estudiantes estudiante = buscarPorId(id);
        repository.delete(estudiante);
    }
}

