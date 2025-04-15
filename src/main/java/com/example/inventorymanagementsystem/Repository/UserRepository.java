package com.example.inventorymanagementsystem.Repository;

import com.example.inventorymanagementsystem.ENUMS.UserRoles;
import com.example.inventorymanagementsystem.Models.Store;
import com.example.inventorymanagementsystem.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByEmployeeNumber(String employeeNumber);

    boolean existsByFirstNameAndLastNameAndUserRoles(
            String firstName,
            String lastName,
            UserRoles userRoles
    );

}
