package com.example.notas.repository;

import com.example.notas.model.Estudiantes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudiantesRepository extends JpaRepository<Estudiantes, Integer> {
    Optional<Estudiantes> findByEmail(String email);
}

