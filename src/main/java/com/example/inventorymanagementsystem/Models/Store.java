package com.example.inventorymanagementsystem.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
public class Store {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID storeId;

    @NotNull
    @NotBlank
    private String storeAddress;

    @NotNull
    @NotBlank
    private String contactNumber;

    public Store() {}
    public Store(UUID storeId, String storeAddress, String contactNumber) {
        this.storeId = storeId;
        this.storeAddress = storeAddress;
        this.contactNumber = contactNumber;
    }

    public UUID getStoreId() {
        return storeId;
    }

    public void setStoreId(UUID storeId) {
        this.storeId = storeId;
    }



    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
