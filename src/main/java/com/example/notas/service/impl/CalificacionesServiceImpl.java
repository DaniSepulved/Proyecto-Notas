// CalificacionesServiceImpl.java
package com.example.notas.service.impl;

import com.example.notas.dto.CalificacionesDTO;
import com.example.notas.model.Calificaciones;
import com.example.notas.model.Estudiantes;
import com.example.notas.model.Asignaturas;
import com.example.notas.repository.CalificacionesRepository;
import com.example.notas.repository.EstudiantesRepository;
import com.example.notas.repository.AsignaturasRepository;
import com.example.notas.service.CalificacionesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionesServiceImpl implements CalificacionesService {

    private final CalificacionesRepository calificacionesRepository;
    private final EstudiantesRepository estudiantesRepository;
    private final AsignaturasRepository asignaturasRepository;

    public CalificacionesServiceImpl(CalificacionesRepository calificacionesRepository,
                                     EstudiantesRepository estudiantesRepository,
                                     AsignaturasRepository asignaturasRepository) {
        this.calificacionesRepository = calificacionesRepository;
        this.estudiantesRepository = estudiantesRepository;
        this.asignaturasRepository = asignaturasRepository;
    }

    @Override
    public Calificaciones crear(CalificacionesDTO dto) {
        Estudiantes estudiante = estudiantesRepository.findById(dto.getEstudianteId())
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado"));

        Asignaturas asignatura = asignaturasRepository.findById(dto.getAsignaturaId())
                .orElseThrow(() -> new EntityNotFoundException("Asignatura no encontrada"));

        Calificaciones calificacion = new Calificaciones();
        calificacion.setEstudiante(estudiante);
        calificacion.setAsignatura(asignatura);
        calificacion.setNota(dto.getNota());

        return calificacionesRepository.save(calificacion);
    }

    @Override
    public List<Calificaciones> listar() {
        return calificacionesRepository.findAll();
    }

    @Override
    public Calificaciones buscarPorId(Integer id) {
        return calificacionesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Calificación no encontrada con id: " + id));
    }

    @Override
    public Calificaciones actualizar(Integer id, CalificacionesDTO dto) {
        Calificaciones calificacion = buscarPorId(id);

        Estudiantes estudiante = estudiantesRepository.findById(dto.getEstudianteId())
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado"));

        Asignaturas asignatura = asignaturasRepository.findById(dto.getAsignaturaId())
                .orElseThrow(() -> new EntityNotFoundException("Asignatura no encontrada"));

        calificacion.setEstudiante(estudiante);
        calificacion.setAsignatura(asignatura);
        calificacion.setNota(dto.getNota());

        return calificacionesRepository.save(calificacion);
    }

    @Override
    public void eliminar(Integer id) {
        Calificaciones calificacion = buscarPorId(id);
        calificacionesRepository.delete(calificacion);
    }
}


