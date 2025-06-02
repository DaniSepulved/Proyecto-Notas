package com.example.notas.service;

import com.example.notas.dto.AsignaturasDTO;
import com.example.notas.model.Asignaturas;
import java.util.List;

public interface AsignaturasService {
    Asignaturas crear(AsignaturasDTO dto);
    List<Asignaturas> listar();
    Asignaturas buscarPorId(String id);
    Asignaturas actualizar(String id, AsignaturasDTO dto);
    void eliminar(String id);
    List<Asignaturas> buscarPorProfesor(String profesorId);
    Asignaturas buscarPorNombre(String nombre);
}