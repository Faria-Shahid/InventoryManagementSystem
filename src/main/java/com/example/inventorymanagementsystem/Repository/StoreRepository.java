package com.example.inventorymanagementsystem.Repository;

import com.example.inventorymanagementsystem.Models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<Store,UUID> {
    public Optional<Store> findStoreByStoreAddress(String address);
}
