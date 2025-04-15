package com.example.inventorymanagementsystem.Controllers;

import com.example.inventorymanagementsystem.DTO.UserRegistrationRequestDTO;
import com.example.inventorymanagementsystem.Models.Users;
import com.example.inventorymanagementsystem.Services.EmployeeRegistration.EmployeeRegistrationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class EmployeeRegistrationController {
    public final EmployeeRegistrationServiceImpl employeeRegistrationService;

    public EmployeeRegistrationController(EmployeeRegistrationServiceImpl employeeRegistrationService) {
        this.employeeRegistrationService = employeeRegistrationService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO){
        Optional<Users> user = employeeRegistrationService.registerEmployee(userRegistrationRequestDTO);

        if (user.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(user.get());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User could not be registered.");
    }

}
