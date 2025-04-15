package com.example.inventorymanagementsystem.Utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class CredentialGenerator {
    private static final SecureRandom random = new SecureRandom();

    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lower = upper.toLowerCase();
    private static final String digits = "0123456789";
    private static final String special = "!@#$%^&*()-_+=<>?";
    private static final String allChars = upper + lower + digits + special;

    public String generateRandomPassword(int length) {
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allChars.length());
            password.append(allChars.charAt(index));
        }

        return password.toString();
    }

    // Generates an employee number like "EMP123456"
    public String generateEmployeeNumber() {
        int number = 100000 + random.nextInt(900000);
        return "EMP" + number;
    }
}

