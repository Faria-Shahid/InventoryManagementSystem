package com.example.inventorymanagementsystem.DTO;

import lombok.Data;

@Data
public class StoreProductResponse {
    private String productId;
    private String productName;
    private int quantity;
    private String storeId;
}
