package com.example.notas.controller;

import com.example.notas.dto.AsignaturasDTO;
import com.example.notas.model.Asignaturas;
import com.example.notas.service.AsignaturasService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturasController {

    private final AsignaturasService asignaturasService;

    public AsignaturasController(AsignaturasService asignaturasService) {
        this.asignaturasService = asignaturasService;
    }

    @PostMapping
    public ResponseEntity<Asignaturas> crear(@Valid @RequestBody AsignaturasDTO dto) {
        return ResponseEntity.ok(asignaturasService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<Asignaturas>> listar() {
        return ResponseEntity.ok(asignaturasService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asignaturas> buscarPorId(@PathVariable String id) { // Cambiado a String
        return ResponseEntity.ok(asignaturasService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asignaturas> actualizar(@PathVariable String id, @Valid @RequestBody AsignaturasDTO dto) { // Cambiado a String
        return ResponseEntity.ok(asignaturasService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) { // Cambiado a String
        asignaturasService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}