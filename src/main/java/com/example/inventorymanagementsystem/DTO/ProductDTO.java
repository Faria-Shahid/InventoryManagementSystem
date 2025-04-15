package com.example.inventorymanagementsystem.DTO;

import lombok.Data;

@Data
public class ProductDTO {
    private String categoryId;
    private String productName;
    private String productDescription;
    private int price;
}
