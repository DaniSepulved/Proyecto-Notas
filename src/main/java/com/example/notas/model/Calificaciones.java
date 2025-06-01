package com.example.notas.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "Calificaciones", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_estudiante", "id_asignatura", "fecha"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calificaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCalificacion;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiantes estudiante;

    @ManyToOne
    @JoinColumn(name = "id_asignatura", nullable = false)
    private Asignaturas asignatura;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal nota;


    @Column(nullable = false)
    private LocalDate fecha;
}
