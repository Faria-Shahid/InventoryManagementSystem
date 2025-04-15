package com.example.inventorymanagementsystem.Services.StockMovement;

import com.example.inventorymanagementsystem.ENUMS.MovementType;
import com.example.inventorymanagementsystem.Models.StockMovement;
import com.example.inventorymanagementsystem.Models.StoreProduct;

import java.time.LocalDateTime;
import java.util.List;

public interface StockMovementService {
    void trackStockMovement(StoreProduct storeProduct, int productBefore, int productAfter, MovementType movementType);

    List<StockMovement> getAllStockMovements();

    List<StockMovement> getAllMovementsForStoreBetweenTime(String storeId, LocalDateTime startTime, LocalDateTime endTime);
}
