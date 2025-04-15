package com.example.inventorymanagementsystem.Controllers;

import com.example.inventorymanagementsystem.DTO.ProductQuantityChangeDTO;
import com.example.inventorymanagementsystem.DTO.StockInDTO;
import com.example.inventorymanagementsystem.Models.StoreProduct;
import com.example.inventorymanagementsystem.Services.StoreProduct.StoreProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreProductController {
    private final StoreProductServiceImpl storeProductServiceImpl;

    public StoreProductController(StoreProductServiceImpl storeProductServiceImpl) {
        this.storeProductServiceImpl = storeProductServiceImpl;
    }

    @PostMapping("/{storeId}/stock-in")
    public ResponseEntity<String> stockIn(@PathVariable String storeId,
                                          @RequestBody StockInDTO stockInDTO) {
        boolean flag = storeProductServiceImpl.addProductToStore(storeId, stockInDTO);

        if (flag) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Product added to stock.");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product could not be added.");
    }

    @PostMapping("/{storeId}/sales")
    public ResponseEntity<String> sales(@PathVariable String storeId,
                                        @RequestBody ProductQuantityChangeDTO productQuantityChangeDTO){
        boolean flag = storeProductServiceImpl.sale(storeId, productQuantityChangeDTO);

        if (flag) {
            return ResponseEntity.status(HttpStatus.OK).body("Product sold successfully.");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sale failed.");
    }

    @PostMapping("/{storeId}/manualRemoval")
    public ResponseEntity<String> manualRemoval(@PathVariable String storeId,
                                        @RequestBody ProductQuantityChangeDTO productQuantityChangeDTO){
        boolean flag = storeProductServiceImpl.manualRemoval(storeId, productQuantityChangeDTO);

        if (flag) {
            return ResponseEntity.status(HttpStatus.OK).body("Product removed successfully successfully.");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Action failed.");
    }

    @GetMapping("/stores/{storeId}/getProducts")
    public ResponseEntity<?> getAllProductsInStore(@PathVariable String uuid){
        List<StoreProduct> storeProductList = storeProductServiceImpl.getAllProductsInStore(uuid);

        if (storeProductList.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("No products in store");
        }

        return ResponseEntity.status(HttpStatus.OK).body(storeProductList);
    }


}
