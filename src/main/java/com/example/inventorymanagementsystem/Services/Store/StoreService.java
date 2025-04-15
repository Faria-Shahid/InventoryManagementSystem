package com.example.inventorymanagementsystem.Services.Store;

import com.example.inventorymanagementsystem.DTO.StoreGenerationRequestDTO;
import com.example.inventorymanagementsystem.Models.Store;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    List<Store> getAllStores();

    Optional<Store> getStoreFromId(String uuid);

    boolean addStore(StoreGenerationRequestDTO storeGenerationRequestDTO);

    boolean removeStore(String uuid);

    boolean updateStoreDetails(String uuid, StoreGenerationRequestDTO storeUpdateRequestDTO);
}
