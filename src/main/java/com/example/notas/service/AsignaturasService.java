package com.example.notas.service;

import com.example.notas.dto.AsignaturasDTO;
import com.example.notas.model.Asignaturas;

import java.util.List;

public interface AsignaturasService {
    Asignaturas crear(AsignaturasDTO dto);
    List<Asignaturas> listar();
    Asignaturas buscarPorId(Integer id);
    Asignaturas actualizar(Integer id, AsignaturasDTO dto);
    void eliminar(Integer id);
}
