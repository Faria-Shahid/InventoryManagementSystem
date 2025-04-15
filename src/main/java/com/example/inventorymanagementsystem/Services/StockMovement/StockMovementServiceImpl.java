package com.example.inventorymanagementsystem.Services.StockMovement;

import com.example.inventorymanagementsystem.ENUMS.MovementType;
import com.example.inventorymanagementsystem.Models.StockMovement;
import com.example.inventorymanagementsystem.Models.StoreProduct;
import com.example.inventorymanagementsystem.Repository.StockMovementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class StockMovementServiceImpl implements StockMovementService {
    private final StockMovementRepository stockMovementRepository;

    public StockMovementServiceImpl(StockMovementRepository stockMovementRepository) {
        this.stockMovementRepository = stockMovementRepository;
    }

    @Override
    public void trackStockMovement(StoreProduct storeProduct,int productBefore, int productAfter, MovementType movementType) {
        StockMovement stockMovement = new StockMovement(storeProduct,productBefore,productAfter,movementType,LocalDateTime.now());

        stockMovementRepository.save(stockMovement);
    }

    @Override
    public List<StockMovement> getAllStockMovements(){
        return stockMovementRepository.findAll();
    }

    @Override
    public List<StockMovement> getAllMovementsForStoreBetweenTime(String storeId, LocalDateTime startTime, LocalDateTime endTime){
        UUID id = UUID.fromString(storeId);
        return stockMovementRepository.getStockMovementByStoreProductContainingAndTimeAddedBetween(id, startTime, endTime);
    }


}
