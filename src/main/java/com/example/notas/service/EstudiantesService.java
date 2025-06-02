package com.example.notas.service;

import com.example.notas.dto.EstudiantesDTO;
import com.example.notas.model.Estudiantes;

import jakarta.validation.Valid;

import java.util.List;

public interface EstudiantesService {
    Estudiantes crear(EstudiantesDTO dto);
    List<Estudiantes> listar();
    Estudiantes buscarPorId(String id);
    Estudiantes actualizar(String id, EstudiantesDTO dto);
    void eliminar(String id);
}

