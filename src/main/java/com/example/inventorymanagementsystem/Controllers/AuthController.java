package com.example.inventorymanagementsystem.Controllers;

import com.example.inventorymanagementsystem.DTO.AuthRequest;
import com.example.inventorymanagementsystem.DTO.AuthResponse;
import com.example.inventorymanagementsystem.Security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            // Authenticate using employeeNumber as username and password
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getEmployeeNumber(), authRequest.getPassword()));

            // Generate JWT token if authentication was successful
            String token = jwtUtil.generateToken(authRequest.getEmployeeNumber());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body("Invalid employee number or password");
        }
    }
}




