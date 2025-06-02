package com.example.notas.service.impl;

import com.example.notas.dto.CalificacionesDTO;
import com.example.notas.model.*;
import com.example.notas.repository.*;
import com.example.notas.service.CalificacionesService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class CalificacionesServiceImpl implements CalificacionesService {

    private final CalificacionesRepository repository;
    private final EstudiantesRepository estudiantesRepository;
    private final AsignaturasRepository asignaturasRepository;

    public CalificacionesServiceImpl(CalificacionesRepository repository,
                                   EstudiantesRepository estudiantesRepository,
                                   AsignaturasRepository asignaturasRepository) {
        this.repository = repository;
        this.estudiantesRepository = estudiantesRepository;
        this.asignaturasRepository = asignaturasRepository;
    }

    @Override
    public Calificaciones crear(CalificacionesDTO dto) {
        Estudiantes estudiante = estudiantesRepository.findById(dto.getEstudianteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante no encontrado"));
        
        Asignaturas asignatura = asignaturasRepository.findById(dto.getAsignaturaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignatura no encontrada"));
        
        Calificaciones calificacion = Calificaciones.builder()
                .estudiante(estudiante)
                .asignatura(asignatura)
                .nota(dto.getNota())
                .fecha(dto.getFecha() != null ? dto.getFecha() : LocalDate.now())
                .build();
        
        return repository.save(calificacion);
    }

    @Override
    public List<Calificaciones> listar() {
        return repository.findAll();
    }

    @Override
    public Calificaciones buscarPorId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Calificación no encontrada"));
    }

    @Override
    public Calificaciones actualizar(String id, CalificacionesDTO dto) {
        Calificaciones calificacion = buscarPorId(id);
        Estudiantes estudiante = estudiantesRepository.findById(dto.getEstudianteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante no encontrado"));
        
        Asignaturas asignatura = asignaturasRepository.findById(dto.getAsignaturaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignatura no encontrada"));

        calificacion.setEstudiante(estudiante);
        calificacion.setAsignatura(asignatura);
        calificacion.setNota(dto.getNota());
        calificacion.setFecha(dto.getFecha() != null ? dto.getFecha() : LocalDate.now());

        return repository.save(calificacion);
    }

    @Override
    public void eliminar(String id) {
        Calificaciones calificacion = buscarPorId(id);
        repository.delete(calificacion);
    }

    @Override
    public List<Calificaciones> listarPorEstudiante(String estudianteId) {
        return repository.findByEstudianteId(estudianteId);
    }

    @Override
    public List<Calificaciones> listarPorAsignatura(String asignaturaId) {
        return repository.findByAsignaturaId(asignaturaId);
    }

    @Override
    public BigDecimal calcularPromedioPorEstudiante(String estudianteId) {
        List<Calificaciones> calificaciones = listarPorEstudiante(estudianteId);
        if (calificaciones.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return calificaciones.stream()
                .map(Calificaciones::getNota)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(calificaciones.size()), 2, RoundingMode.HALF_UP);
    }
}