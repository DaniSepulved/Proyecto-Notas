package com.example.notas.repository;

import com.example.notas.model.Profesores;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// ProfesoresRepository.java
@Repository
public interface ProfesoresRepository extends MongoRepository<Profesores, String> {
    Optional<Profesores> findByEmail(String email); 
}
