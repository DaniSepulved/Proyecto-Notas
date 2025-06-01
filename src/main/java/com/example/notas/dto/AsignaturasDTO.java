package com.example.notas.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturasDTO {
    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;
}
