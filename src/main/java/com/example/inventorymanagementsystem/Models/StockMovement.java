package com.example.inventorymanagementsystem.Models;

import com.example.inventorymanagementsystem.ENUMS.MovementType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class StockMovement {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @NotNull
    private StoreProduct storeProduct;

    @NotNull
    private int stockBeforeMovement;

    @NotNull
    private int stockAfterMovement;

    @Enumerated(EnumType.STRING)
    @NotNull
    private MovementType movementType;

    @NotNull
    private LocalDateTime timeAdded;

    public StockMovement() {}

    public StockMovement(StoreProduct storeProduct, int stockBeforeMovement, int stockAfterMovement, MovementType movementType, LocalDateTime timeAdded) {
        this.storeProduct = storeProduct;
        this.stockBeforeMovement = stockBeforeMovement;
        this.stockAfterMovement = stockAfterMovement;
        this.movementType = movementType;
        this.timeAdded = timeAdded;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public StoreProduct getStoreProduct() {
        return storeProduct;
    }

    public void setStoreProduct(StoreProduct storeProduct) {
        this.storeProduct = storeProduct;
    }

    public int getStockBeforeMovement() {
        return stockBeforeMovement;
    }

    public void setStockBeforeMovement(int stockBeforeMovement) {
        this.stockBeforeMovement = stockBeforeMovement;
    }

    public int getStockAfterMovement() {
        return stockAfterMovement;
    }

    public void setStockAfterMovement(int stockAfterMovement) {
        this.stockAfterMovement = stockAfterMovement;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public LocalDateTime getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(LocalDateTime timeAdded) {
        this.timeAdded = timeAdded;
    }
}
