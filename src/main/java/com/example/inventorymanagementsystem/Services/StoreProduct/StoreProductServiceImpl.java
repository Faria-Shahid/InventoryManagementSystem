package com.example.inventorymanagementsystem.Services.StoreProduct;

import com.example.inventorymanagementsystem.DTO.ProductQuantityChangeDTO;
import com.example.inventorymanagementsystem.DTO.StockInDTO;
import com.example.inventorymanagementsystem.ENUMS.MovementType;
import com.example.inventorymanagementsystem.Models.Product;
import com.example.inventorymanagementsystem.Models.Store;
import com.example.inventorymanagementsystem.Models.StoreProduct;
import com.example.inventorymanagementsystem.Repository.StoreProductRepository;
import com.example.inventorymanagementsystem.Services.Product.ProductServiceImpl;
import com.example.inventorymanagementsystem.Services.StockMovement.StockMovementServiceImpl;
import com.example.inventorymanagementsystem.Services.Store.StoreServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class StoreProductServiceImpl implements StoreProductService {
    private final StoreProductRepository storeProductRepository;
    private final StockMovementServiceImpl stockMovementServiceImpl;
    private final StoreServiceImpl storeServiceImpl;

    private final ProductServiceImpl productServiceImpl;

    public StoreProductServiceImpl(StoreProductRepository storeProductRepository, StockMovementServiceImpl stockMovementServiceImpl, StoreServiceImpl storeServiceImpl, ProductServiceImpl productServiceImpl) {
        this.storeProductRepository = storeProductRepository;
        this.stockMovementServiceImpl = stockMovementServiceImpl;
        this.storeServiceImpl = storeServiceImpl;
        this.productServiceImpl = productServiceImpl;
    }

    @Override
    public Optional<StoreProduct> getStoreProduct(String storeId, String productId) {
        try {
            UUID storeUUID = UUID.fromString(storeId);
            UUID productUUID = UUID.fromString(productId);
            return storeProductRepository.findByProduct_ProductIdAndStore_StoreId(productUUID, storeUUID);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean processStockChange(String storeId, String productId, int quantity, MovementType movementType) {
        Optional<StoreProduct> optionalStoreProduct = getStoreProduct(storeId, productId);

        if (optionalStoreProduct.isEmpty()) {
            System.out.println("Product not found or invalid ID: " + productId);
            return false;
        }

        StoreProduct storeProduct = optionalStoreProduct.get();
        int currentStock = storeProduct.getQuantityInStock();

        if (quantity > currentStock) {
            System.out.println("Insufficient stock for product: " + productId);
            return false;
        }

        int updatedStock = currentStock - quantity;
        storeProduct.setQuantityInStock(updatedStock);
        storeProductRepository.save(storeProduct);

        stockMovementServiceImpl.trackStockMovement(storeProduct, currentStock, updatedStock, movementType);
        return true;
    }


    @Override
    public boolean addProductToStore(String storeId, StockInDTO stockInDTO) {
        UUID productUUID = UUID.fromString(stockInDTO.getProductId());
        Product product = productServiceImpl.getProductById(productUUID);

        if (product != null) {
            Optional<Store> optionalStore = storeServiceImpl.getStoreFromId(storeId);
            if (optionalStore.isEmpty()) {
                return false;
            }

            if (getStoreProduct(storeId, stockInDTO.getProductId()).isEmpty()) {


                StoreProduct storeProduct = new StoreProduct();
                storeProduct.setStore(optionalStore.get());
                storeProduct.setProduct(product);
                storeProduct.setQuantityInStock(stockInDTO.getQuantity());

                storeProductRepository.save(storeProduct);
                stockMovementServiceImpl.trackStockMovement(storeProduct, 0, storeProduct.getQuantityInStock(), MovementType.STOCK_IN);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean updateProductStock(String productId, String storeId, int prodQuantity) {
        Optional<StoreProduct> optionalStoreProduct = getStoreProduct(storeId, productId);

        if (optionalStoreProduct.isPresent()) {
            StoreProduct storeProduct = optionalStoreProduct.get();
            int prevStock = storeProduct.getQuantityInStock();

            storeProduct.setQuantityInStock(prevStock + prodQuantity);
            storeProductRepository.save(storeProduct);
            stockMovementServiceImpl.trackStockMovement(storeProduct, prevStock, storeProduct.getQuantityInStock(), MovementType.STOCK_IN);

            return true;
        }

        return false;
    }


    @Override
    @Transactional
    public boolean sale(String storeId, ProductQuantityChangeDTO productQuantityChangeDTO) {
        Map<String,Integer> productSold = productQuantityChangeDTO.getProductsToBeChanged();

        for (Map.Entry<String, Integer> entry : productSold.entrySet()) {
            String productId = entry.getKey();
            int quantity = entry.getValue();

            boolean success = processStockChange(storeId, productId, quantity, MovementType.SALE);
            if (!success) return false;
        }
        return true;
    }


    @Override
    @Transactional
    public boolean manualRemoval(String storeId, ProductQuantityChangeDTO productQuantityChangeDTO) {
        Map<String,Integer> productRemoved = productQuantityChangeDTO.getProductsToBeChanged();

        for (Map.Entry<String, Integer> entry : productRemoved.entrySet()) {
            String productId = entry.getKey();
            int quantity = entry.getValue();

            boolean success = processStockChange(storeId, productId, quantity, MovementType.MANUAL_REMOVAL);
            if (!success) return false;
        }
        return true;
    }

    public List<StoreProduct> getAllProductsInStore(String storeId){
        UUID storeUUID = UUID.fromString(storeId);
        return storeProductRepository.findAllByStore_StoreId(storeUUID);
    }


}
