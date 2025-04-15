package com.example.inventorymanagementsystem.Repository;


import com.example.inventorymanagementsystem.Models.StoreProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StoreProductRepository extends JpaRepository<StoreProduct,UUID> {
    Optional<StoreProduct> findByProduct_ProductIdAndStore_StoreId(UUID productId, UUID storeId);

    List<StoreProduct> findAllByStore_StoreId(UUID storeId);
}
