package com.example.notas.repository;

import com.example.notas.model.Asignaturas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturasRepository extends JpaRepository<Asignaturas, Integer> {
}
