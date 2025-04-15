package com.example.inventorymanagementsystem.Controllers;

import com.example.inventorymanagementsystem.Models.StockMovement;
import com.example.inventorymanagementsystem.Services.StockMovement.StockMovementServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class StockMovementController {
    private final StockMovementServiceImpl stockMovementServiceImpl;

    public StockMovementController(StockMovementServiceImpl stockMovementServiceImpl) {
        this.stockMovementServiceImpl = stockMovementServiceImpl;
    }

    @GetMapping("/stock/history")
    public ResponseEntity<?> getAllMovements(){
        List<StockMovement> stockMovementList = stockMovementServiceImpl.getAllStockMovements();

        if (stockMovementList.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("No movements found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(stockMovementList);
    }

    @GetMapping("/stock/history-store")
    public ResponseEntity<?> getStoreSpecificMovementsBetweenTimePeriod(
            @RequestParam String storeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {

        List<StockMovement> stockMovementList = stockMovementServiceImpl.getAllMovementsForStoreBetweenTime(storeId,startTime,endTime);

        if (stockMovementList.isEmpty()) {
            return ResponseEntity.ok("No movements found for given store and time.");
        }

        return ResponseEntity.ok(stockMovementList);
    }

}
