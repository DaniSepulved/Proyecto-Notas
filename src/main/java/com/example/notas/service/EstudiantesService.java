package com.example.notas.service;

import com.example.notas.dto.EstudiantesDTO;
import com.example.notas.model.Estudiantes;

import java.util.List;

public interface EstudiantesService {
    Estudiantes crear(EstudiantesDTO estudianteDTO);
    List<Estudiantes> listar();
    Estudiantes buscarPorId(String id);
    Estudiantes actualizar(String id, EstudiantesDTO estudianteDTO);
    void eliminar(String id);
}


