package com.example.inventorymanagementsystem.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class StoreProduct {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID storeProductId;

    @ManyToOne
    private Store store;

    @ManyToOne
    private Product product;

    private int quantityInStock;

    public StoreProduct() {}

    public StoreProduct(Store store, Product product, int quantityInStock) {
        this.store = store;
        this.product = product;
        this.quantityInStock = quantityInStock;
    }

    public UUID getStoreProductId() {
        return storeProductId;
    }

    public void setStoreProductId(UUID storeProductId) {
        this.storeProductId = storeProductId;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
