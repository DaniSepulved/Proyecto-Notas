package com.example.notas.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AsignaturasDTO {
    @NotBlank
    private String nombre;
    
    @NotBlank
    private String profesorId; // Cambiado a String para el ID del profesor
}