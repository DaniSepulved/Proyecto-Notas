package com.example.notas.service;

import com.example.notas.dto.EstudiantesDTO;
import com.example.notas.model.Estudiantes;

import java.util.List;

public interface EstudiantesService {
    Estudiantes crear(EstudiantesDTO dto);
    List<Estudiantes> listar();
    Estudiantes buscarPorId(Integer id); // Usar Integer
    Estudiantes actualizar(Integer id, EstudiantesDTO dto); // Usar Integer
    void eliminar(Integer id); // Usar Integer
}

