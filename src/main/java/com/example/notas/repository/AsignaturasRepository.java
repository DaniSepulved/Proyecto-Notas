package com.example.notas.repository;

import com.example.notas.model.Asignaturas;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface AsignaturasRepository extends MongoRepository<Asignaturas, String> {
    List<Asignaturas> findByProfesorId(String profesorId);
    Optional<Asignaturas> findByNombre(String nombre); // Añade este método
}