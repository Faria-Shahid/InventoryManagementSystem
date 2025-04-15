package com.example.inventorymanagementsystem.Services.EmployeeRegistration;

import com.example.inventorymanagementsystem.DTO.UserRegistrationRequestDTO;
import com.example.inventorymanagementsystem.ENUMS.UserRoles;
import com.example.inventorymanagementsystem.Mapping.UserMapper;
import com.example.inventorymanagementsystem.Models.Users;
import com.example.inventorymanagementsystem.Repository.UserRepository;
import com.example.inventorymanagementsystem.Utility.CredentialGenerator;
import com.example.inventorymanagementsystem.Utility.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeRegistrationServiceImpl implements EmployeeRegistrationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CredentialGenerator credentialGenerator;
    private final PasswordEncoder passwordEncoder;

    public EmployeeRegistrationServiceImpl(UserRepository userRepository, UserMapper userMapper, CredentialGenerator credentialGenerator, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.credentialGenerator = credentialGenerator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<Users> registerEmployee(UserRegistrationRequestDTO userRegistrationRequestDTO) {
        UserRoles role = UserRoles.valueOf(userRegistrationRequestDTO.getRole().toUpperCase());

        boolean userExists = userRepository.existsByFirstNameAndLastNameAndUserRoles(
                    userRegistrationRequestDTO.getFirstName(),
                    userRegistrationRequestDTO.getLastName(),
                    role
            );

            if (userExists) {
                System.out.println("User already exists in the store.");
                return Optional.empty();
            }

            Users users = userMapper.toEntity(userRegistrationRequestDTO);

            String employeeNumber = credentialGenerator.generateEmployeeNumber();
            String rawPassword = credentialGenerator.generateRandomPassword(8);
            String hashedPassword = passwordEncoder.hashPassword(rawPassword);

            users.setEmployeeNumber(employeeNumber);
            users.setPassword(hashedPassword);

            userRepository.save(users);

            return Optional.of(users);
    }



}
