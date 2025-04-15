package com.example.inventorymanagementsystem.Mapping;

import com.example.inventorymanagementsystem.DTO.StoreProductResponse;
import com.example.inventorymanagementsystem.Models.StoreProduct;
import com.example.inventorymanagementsystem.Utility.UuidMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = UuidMapper.class)
public interface StoreProductMapper {

    @Mapping(source = "product.productId", target = "productId", qualifiedByName = "uuidToString")
    @Mapping(source = "product.productName", target = "productName")
    @Mapping(source = "quantityInStock", target = "quantity")
    @Mapping(source = "store.storeId", target = "storeId", qualifiedByName = "uuidToString")
    StoreProductResponse toStoreProductResponse(StoreProduct storeProduct);
}
