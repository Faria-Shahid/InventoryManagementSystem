package com.example.inventorymanagementsystem.DTO;

import lombok.Data;

@Data
public class AuthRequest {
    private String employeeNumber;
    private String password;
}