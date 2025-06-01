package com.example.notas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Profesores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profesores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProfesor;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;
}

