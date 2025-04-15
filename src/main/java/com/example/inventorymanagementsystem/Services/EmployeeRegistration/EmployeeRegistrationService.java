package com.example.inventorymanagementsystem.Services.EmployeeRegistration;

import com.example.inventorymanagementsystem.DTO.UserRegistrationRequestDTO;
import com.example.inventorymanagementsystem.Models.Users;

import java.util.Optional;

public interface EmployeeRegistrationService {

    Optional<Users> registerEmployee(UserRegistrationRequestDTO userRegistrationRequestDTO);
}
