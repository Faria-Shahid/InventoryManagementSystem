package com.example.inventorymanagementsystem.Services;

import com.example.inventorymanagementsystem.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String employeeNumber) throws UsernameNotFoundException {
        return userRepository.findByEmployeeNumber(employeeNumber)
                .orElseThrow(() -> new UsernameNotFoundException("Employee number not found: " + employeeNumber));
    }
}

