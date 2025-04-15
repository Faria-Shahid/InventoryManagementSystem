package com.example.inventorymanagementsystem.Utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Hash a plain text password
    public String hashPassword(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    // Check if a plain password matches the hashed one
    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }
}
