package com.example.inventorymanagementsystem.Repository;

import com.example.inventorymanagementsystem.Models.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement,UUID> {

    @Query("SELECT sm FROM StockMovement sm " +
            "JOIN sm.storeProduct sp " +
            "JOIN sp.store s " +
            "WHERE s.storeId = :storeId " +
            "AND sm.timeAdded BETWEEN :startDateTime AND :endDateTime")
    public List<StockMovement> getStockMovementByStoreProductContainingAndTimeAddedBetween(UUID storeId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
