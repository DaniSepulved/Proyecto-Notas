package com.example.notas.service.impl;

import com.example.notas.dto.AsignaturasDTO;
import com.example.notas.model.Asignaturas;
import com.example.notas.repository.AsignaturasRepository;
import com.example.notas.service.AsignaturasService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class AsignaturasServiceImpl implements AsignaturasService {

    private final AsignaturasRepository repository;

    public AsignaturasServiceImpl(AsignaturasRepository repository) {
        this.repository = repository;
    }

    @Override
    public Asignaturas crear(AsignaturasDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de la asignatura es requerido");
        }

        if (dto.getProfesorId() == null || dto.getProfesorId().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID del profesor es requerido");
        }

        Asignaturas asignatura = new Asignaturas();
        asignatura.setNombre(dto.getNombre());
        asignatura.setProfesorId(dto.getProfesorId());
        
        return repository.save(asignatura);
    }

    @Override
    public List<Asignaturas> listar() {
        return repository.findAll();
    }

    @Override
    public Asignaturas buscarPorId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignatura no encontrada con ID: " + id));
    }

    @Override
    public Asignaturas actualizar(String id, AsignaturasDTO dto) {
        Asignaturas asignaturaExistente = buscarPorId(id);
        
        if (dto.getNombre() != null && !dto.getNombre().isEmpty()) {
            asignaturaExistente.setNombre(dto.getNombre());
        }
        
        if (dto.getProfesorId() != null && !dto.getProfesorId().isEmpty()) {
            asignaturaExistente.setProfesorId(dto.getProfesorId());
        }
        
        return repository.save(asignaturaExistente);
    }

    @Override
    public void eliminar(String id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignatura no encontrada con ID: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<Asignaturas> buscarPorProfesor(String profesorId) {
        if (profesorId == null || profesorId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID del profesor es requerido");
        }
        return repository.findByProfesorId(profesorId);
    }

    @Override
    public Asignaturas buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignatura no encontrada con nombre: " + nombre));
    }
}