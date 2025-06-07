package com.example.notas.repository;

import com.example.notas.model.Estudiantes;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// EstudiantesRepository.java
// @Repository
// public interface EstudiantesRepository extends MongoRepository<Estudiantes, String> {
//     Optional<Estudiantes> findByEmail(String email);
// }

@Repository
public interface EstudiantesRepository extends MongoRepository<Estudiantes, String> {
    Optional<Estudiantes> findByEmail(String email);
}


