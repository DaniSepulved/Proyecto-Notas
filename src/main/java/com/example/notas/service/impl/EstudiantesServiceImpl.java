package com.example.notas.service.impl;

import com.example.notas.model.Estudiantes;
import com.example.notas.repository.EstudiantesRepository;
import com.example.notas.service.EstudiantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class EstudiantesServiceImpl implements EstudiantesService {

    private final EstudiantesRepository repository;

    @Autowired
    public EstudiantesServiceImpl(EstudiantesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Estudiantes crear(Estudiantes estudiante) {
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
    public Estudiantes actualizar(String id, Estudiantes estudianteActualizado) {
        Estudiantes estudianteExistente = buscarPorId(id);
        estudianteExistente.setNombre(estudianteActualizado.getNombre());
        estudianteExistente.setEmail(estudianteActualizado.getEmail());
        estudianteExistente.setPassword(estudianteActualizado.getPassword());
        estudianteExistente.setRol(estudianteActualizado.getRol());
        return repository.save(estudianteExistente);
    }

    @Override
    public void eliminar(String id) {
        Estudiantes estudiante = buscarPorId(id);
        repository.delete(estudiante);
    }
}

