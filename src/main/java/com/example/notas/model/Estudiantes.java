package com.example.notas.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "estudiantes")
public class Estudiantes {

    @Id
    private String id;

    private String nombre;

    private String email;

    private String password;

    private Rol rol;
}
