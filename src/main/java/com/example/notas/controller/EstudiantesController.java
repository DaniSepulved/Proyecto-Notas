package com.example.notas.controller;

import com.example.notas.dto.EstudiantesDTO;
import com.example.notas.model.Estudiantes;
import com.example.notas.service.EstudiantesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudiantesController {

    private final EstudiantesService estudiantesService;

    public EstudiantesController(EstudiantesService estudiantesService) {
        this.estudiantesService = estudiantesService;
    }

    @PostMapping
    public ResponseEntity<Estudiantes> crear(@Valid @RequestBody EstudiantesDTO dto) {
        return ResponseEntity.ok(estudiantesService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<Estudiantes>> listar() {
        return ResponseEntity.ok(estudiantesService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiantes> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(estudiantesService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiantes> actualizar(@PathVariable Integer id, @Valid @RequestBody EstudiantesDTO dto) {
        return ResponseEntity.ok(estudiantesService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        estudiantesService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

