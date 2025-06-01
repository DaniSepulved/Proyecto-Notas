package com.example.notas.repository;

import com.example.notas.model.Profesores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfesoresRepository extends JpaRepository<Profesores, Integer> {
    Optional<Profesores> findByEmail(String email);
}

