package com.example.inventorymanagementsystem.Models;

import com.example.inventorymanagementsystem.ENUMS.UserRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
public class Users implements UserDetails {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID employeeId;

    @NotNull
    @Column(unique = true)
    private String employeeNumber;

    @NotNull
    @Size(min = 8)
    private String password;


    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRoles userRoles;


    public Users() {
    }

    public Users(UUID employeeId, String employeeNumber, String password, String firstName, String lastName, UserRoles userRoles) {
        this.employeeId = employeeId;
        this.employeeNumber = employeeNumber;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRoles = userRoles;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRoles getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(UserRoles userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + userRoles.name());
    }

    @Override
    public String getUsername() {
        return employeeNumber;
    }

    @Override public String getPassword() { return password; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}

