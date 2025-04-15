package com.example.inventorymanagementsystem.Services.StoreProduct;

import com.example.inventorymanagementsystem.DTO.ProductQuantityChangeDTO;
import com.example.inventorymanagementsystem.DTO.StockInDTO;
import com.example.inventorymanagementsystem.ENUMS.MovementType;
import com.example.inventorymanagementsystem.Models.StoreProduct;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface StoreProductService {
    Optional<StoreProduct> getStoreProduct(String storeId, String productId);

    boolean processStockChange(String storeId, String productId, int quantity, MovementType movementType);

    boolean addProductToStore(String storeId, StockInDTO stockInDTO);

    boolean updateProductStock(String productId, String storeId, int prodQuantity);

    @Transactional
    boolean sale(String storeId, ProductQuantityChangeDTO productQuantityChangeDTO);

    @Transactional
    boolean manualRemoval(String storeId, ProductQuantityChangeDTO productQuantityChangeDTO);
}
