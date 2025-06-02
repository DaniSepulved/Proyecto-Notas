package com.example.notas.service;

import com.example.notas.dto.CalificacionesDTO;
import com.example.notas.model.Calificaciones;
import java.math.BigDecimal;
import java.util.List;

public interface CalificacionesService {
    Calificaciones crear(CalificacionesDTO dto);
    List<Calificaciones> listar();
    Calificaciones buscarPorId(String id);
    Calificaciones actualizar(String id, CalificacionesDTO dto);
    void eliminar(String id);
    List<Calificaciones> listarPorEstudiante(String estudianteId);
    List<Calificaciones> listarPorAsignatura(String asignaturaId);
    BigDecimal calcularPromedioPorEstudiante(String estudianteId);
}
