package com.example.notas.service;

import com.example.notas.dto.CalificacionesDTO;
import com.example.notas.model.Calificaciones;

import java.util.List;

public interface CalificacionesService {
    Calificaciones crear(CalificacionesDTO dto);
    List<Calificaciones> listar();
    Calificaciones buscarPorId(Integer id);
    Calificaciones actualizar(Integer id, CalificacionesDTO dto);
    void eliminar(Integer id);
}
