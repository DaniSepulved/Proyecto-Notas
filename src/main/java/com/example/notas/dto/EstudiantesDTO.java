package com.example.notas.dto;

import com.example.notas.model.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class EstudiantesDTO {
//     @NotBlank
//     private String nombre;

//     @NotBlank @Email
//     private String email;

//     @NotBlank
//     private String password;

//     private Rol rol;
// }

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudiantesDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;  // Lo recibes pero no guardas

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String rol;  // Si quieres usarlo, lo tomas aquí, pero yo lo fijo abajo
}
