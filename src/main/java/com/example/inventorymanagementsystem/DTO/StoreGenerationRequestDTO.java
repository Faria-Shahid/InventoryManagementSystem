package com.example.inventorymanagementsystem.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StoreGenerationRequestDTO {
    @NotBlank(message = "Address can't be blank.")
    private String storeAddress;
    @NotBlank(message = "Contact can't be blank.")
    private String contactNumber;
}
