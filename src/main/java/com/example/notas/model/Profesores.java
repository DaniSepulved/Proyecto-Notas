package com.example.notas.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "profesores")
public class Profesores {

    @Id
    private String id;  // Mongo usa String (ObjectId)

    private String nombre;

    private String email;

    private String password;

    private Rol rol;
}
