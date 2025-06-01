package com.example.notas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Asignaturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asignaturas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAsignatura;

    @Column(nullable = false, length = 100)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesores profesor;
}
