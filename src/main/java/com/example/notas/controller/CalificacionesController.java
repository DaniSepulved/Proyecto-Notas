package com.example.notas.controller;

import com.example.notas.dto.CalificacionesDTO;
import com.example.notas.model.Calificaciones;
import com.example.notas.service.CalificacionesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionesController {

    private final CalificacionesService calificacionesService;

    public CalificacionesController(CalificacionesService calificacionesService) {
        this.calificacionesService = calificacionesService;
    }

    @PostMapping
    public ResponseEntity<Calificaciones> crear(@Valid @RequestBody CalificacionesDTO dto) {
        return ResponseEntity.ok(calificacionesService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<Calificaciones>> listar() {
        return ResponseEntity.ok(calificacionesService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calificaciones> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(calificacionesService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Calificaciones> actualizar(@PathVariable Integer id, @Valid @RequestBody CalificacionesDTO dto) {
        return ResponseEntity.ok(calificacionesService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        calificacionesService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
