// CalificacionesDTO.java
package com.example.notas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionesDTO {

    @NotNull
    private Integer estudianteId;

    @NotNull
    private Integer asignaturaId;

    @NotNull
    private BigDecimal nota;
}

