package com.example.inventorymanagementsystem.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryRequestDTO {
    @NotBlank(message = "Category name must not be blank")
    private String categoryName;
}
