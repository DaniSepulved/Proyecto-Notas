package com.example.notas.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CalificacionesDTO {
    private String estudianteId;
    private String asignaturaId;
    private BigDecimal nota;
    private LocalDate fecha;
}
