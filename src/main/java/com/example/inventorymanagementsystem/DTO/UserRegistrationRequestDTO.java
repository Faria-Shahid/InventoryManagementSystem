package com.example.inventorymanagementsystem.DTO;

import lombok.Data;

@Data
public class UserRegistrationRequestDTO {
    private String firstName;
    private String lastName;
    private String role;
}
