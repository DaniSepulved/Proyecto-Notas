package com.example.notas.service.impl;

import com.example.notas.dto.AsignaturasDTO;
import com.example.notas.model.Asignaturas;
import com.example.notas.repository.AsignaturasRepository;
import com.example.notas.service.AsignaturasService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturasServiceImpl implements AsignaturasService {

    private final AsignaturasRepository asignaturasRepository;

    public AsignaturasServiceImpl(AsignaturasRepository asignaturasRepository) {
        this.asignaturasRepository = asignaturasRepository;
    }

    @Override
    public Asignaturas crear(AsignaturasDTO dto) {
        Asignaturas asignatura = new Asignaturas();
        asignatura.setNombre(dto.getNombre());
        return asignaturasRepository.save(asignatura);
    }

    @Override
    public List<Asignaturas> listar() {
        return asignaturasRepository.findAll();
    }

    @Override
    public Asignaturas buscarPorId(Integer id) {
        return asignaturasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada con id " + id));
    }

    @Override
    public Asignaturas actualizar(Integer id, AsignaturasDTO dto) {
        Asignaturas asignatura = buscarPorId(id);
        asignatura.setNombre(dto.getNombre());
        return asignaturasRepository.save(asignatura);
    }

    @Override
    public void eliminar(Integer id) {
        asignaturasRepository.deleteById(id);
    }
}
