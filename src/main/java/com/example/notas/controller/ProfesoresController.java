package com.example.notas.controller;

import com.example.notas.dto.ProfesoresDTO;
import com.example.notas.model.Profesores;
import com.example.notas.service.ProfesoresService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesoresController {

    private final ProfesoresService profesoresService;

    public ProfesoresController(ProfesoresService profesoresService) {
        this.profesoresService = profesoresService;
    }

    @PostMapping
    public ResponseEntity<Profesores> crear(@Valid @RequestBody ProfesoresDTO dto) {
        return ResponseEntity.ok(profesoresService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<Profesores>> listar() {
        return ResponseEntity.ok(profesoresService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesores> buscarPorId(@PathVariable String id) { // Cambio de Integer a String
        return ResponseEntity.ok(profesoresService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesores> actualizar(@PathVariable String id, @Valid @RequestBody ProfesoresDTO dto) { // Cambio de Integer a String
        return ResponseEntity.ok(profesoresService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) { // Cambio de Integer a String
        profesoresService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
