package com.example.notas.service;

import com.example.notas.dto.LoginDTO;
import java.util.Map;

public interface AuthService {
    Map<String, String> login(LoginDTO loginDTO);
}
