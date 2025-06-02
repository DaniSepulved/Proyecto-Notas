package com.example.notas.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;

@Document(collection = "asignaturas")
@Data
public class Asignaturas {
    @Id
    private String id;
    
    private String nombre;
    
    @Field("profesor_id") // Opcional: para personalizar el nombre del campo en MongoDB
    private String profesorId; // Guardamos solo el ID del profesor
}
