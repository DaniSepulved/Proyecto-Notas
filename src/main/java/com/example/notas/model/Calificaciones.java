package com.example.notas.model;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "calificaciones")
public class Calificaciones {

    @Id
    private String id;

    @DBRef
    private Estudiantes estudiante;

    @DBRef
    private Asignaturas asignatura;

    private BigDecimal nota;

    private LocalDate fecha;
}
