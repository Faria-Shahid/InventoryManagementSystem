package com.example.inventorymanagementsystem.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
public class ProductQuantityChangeDTO {
    @NotNull
    Map<String,Integer> productsToBeChanged;
}
