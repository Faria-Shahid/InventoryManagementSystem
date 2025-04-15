package com.example.inventorymanagementsystem.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UuidGenerator;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Entity
public class Product {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID productId;

    @NotNull
    @Size(max = 30)
    private String productName;

    @NotNull
    @Size(max = 100)
    private String productDescription;

    @NotNull
    private int price;

    @NotNull
    @ManyToOne
    private Category category;


    public Product() {
    }

    public Product(String productName, String productDescription, int price, Category category) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.category = category;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", price=" + price +
                '}';
    }
}
