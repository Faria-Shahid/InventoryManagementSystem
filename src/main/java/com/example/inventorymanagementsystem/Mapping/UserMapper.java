package com.example.inventorymanagementsystem.Mapping;

import com.example.inventorymanagementsystem.DTO.UserRegistrationRequestDTO;
import com.example.inventorymanagementsystem.Models.Users;
import com.example.inventorymanagementsystem.ENUMS.UserRoles;
import com.example.inventorymanagementsystem.Utility.UuidMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UuidMapper.class, imports = UserRoles.class)
public interface UserMapper {

    @Mapping(target = "employeeId", ignore = true)
    @Mapping(target = "employeeNumber", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "userRoles", expression = "java(UserRoles.valueOf(dto.getRole().toUpperCase()))")
    Users toEntity(UserRegistrationRequestDTO dto);

    @Mapping(target = "role", expression = "java(users.getUserRoles().name().toLowerCase())")
    UserRegistrationRequestDTO toDTO(Users users);
}




