// ProfesoresService.java
package com.example.notas.service;

import com.example.notas.dto.ProfesoresDTO;
import com.example.notas.model.Profesores;
import java.util.List;

public interface ProfesoresService {
    Profesores crear(ProfesoresDTO dto);
    List<Profesores> listar();
    Profesores buscarPorId(Integer id);
    Profesores actualizar(Integer id, ProfesoresDTO dto);
    void eliminar(Integer id);
}
