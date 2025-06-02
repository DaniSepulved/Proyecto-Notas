package com.example.notas.config.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.notas.model.Profesores;
import com.example.notas.model.Rol;
import com.example.notas.repository.ProfesoresRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProfesoresRepository profesoresRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(ProfesoresRepository profesoresRepository, PasswordEncoder passwordEncoder) {
        this.profesoresRepository = profesoresRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (profesoresRepository.findByEmail("admin@admin.com").isEmpty()) {
            Profesores admin = Profesores.builder()
                    .nombre("Administrador Principal")
                    .email("admin@admin.com")
                    .password(passwordEncoder.encode("admin123"))
                    .rol(Rol.ADMIN)
                    .build();

            profesoresRepository.save(admin);
            System.out.println("✅ Profesor administrador creado");
        } else {
            System.out.println("ℹ Profesor administrador ya existe");
        }
    }
}

