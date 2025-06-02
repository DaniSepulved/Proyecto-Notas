package com.example.notas.controller;

import com.example.notas.dto.LoginDTO;
import com.example.notas.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService; // Eliminado el @Qualifier ya que no es necesario

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
        Map<String, String> response = authService.login(loginDTO);
        return ResponseEntity.ok(response);
    }
}
