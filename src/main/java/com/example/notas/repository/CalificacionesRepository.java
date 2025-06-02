package com.example.notas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.notas.model.Calificaciones;

import java.util.List;

public interface CalificacionesRepository extends MongoRepository<Calificaciones, String> {
    List<Calificaciones> findByEstudianteId(String estudianteId);
    List<Calificaciones> findByAsignaturaId(String asignaturaId);
}
