package com.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.LoginResponse;
import com.example.backend.dto.SignupRequest;
import com.example.backend.model.User;
import com.example.backend.repo.UserRepository;
import com.example.backend.security.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void registerUser(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
            .fullName(request.getFullName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .build();

        userRepository.save(user);
    }

//     public LoginResponse login(LoginRequest request) {
//     User user = userRepository.findByEmail(request.getEmail())
//         .orElseThrow(() -> new RuntimeException("User not found"));

//     if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//         throw new RuntimeException("Invalid credentials");
//     }

//     String token = jwtUtil.generateToken(user.getEmail());
//     return new LoginResponse(token, user.getRole());
// }

public LoginResponse login(LoginRequest request) {
    User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Invalid credentials");
    }

    String token = jwtUtil.generateToken(user.getEmail());
    return new LoginResponse(token, user.getRole());
}


}

