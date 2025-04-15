package com.example.inventorymanagementsystem.Controllers;

import com.example.inventorymanagementsystem.DTO.StoreGenerationRequestDTO;
import com.example.inventorymanagementsystem.Models.Store;
import com.example.inventorymanagementsystem.Services.Store.StoreServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreServiceImpl storeServiceImpl;

    public StoreController(StoreServiceImpl storeServiceImpl) {
        this.storeServiceImpl = storeServiceImpl;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllStores(){
        List<Store> stores = storeServiceImpl.getAllStores();

        if (stores.isEmpty()){
            return ResponseEntity.ok().body("No stores found");
        }

        return ResponseEntity.ok(stores);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getStoreById(@PathVariable String uuid){
        Optional<Store> store = storeServiceImpl.getStoreFromId(uuid);

        if (store.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to find Store...");
        }
        return ResponseEntity.ok(store.get());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStore(@Valid @RequestBody StoreGenerationRequestDTO storeGenerationRequestDTO){
        boolean flag = storeServiceImpl.addStore(storeGenerationRequestDTO);

        if (flag){
            return ResponseEntity.status(HttpStatus.CREATED).body("Store created successfully.");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add Store. It may already exist or something went wrong.");
    }

    @PatchMapping("/update/{uuid}")
    public ResponseEntity<String> updateStore(@PathVariable String uuid,@RequestBody StoreGenerationRequestDTO storeGenerationRequestDTO){
        boolean flag = storeServiceImpl.updateStoreDetails(uuid,storeGenerationRequestDTO);

        if (flag){
            return ResponseEntity.status(HttpStatus.OK).body("Store updated successfully.");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add Store. It may not exist or something went wrong.");
    }

}
