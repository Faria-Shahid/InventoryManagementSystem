package com.example.inventorymanagementsystem.Mapping;

import com.example.inventorymanagementsystem.DTO.ProductDTO;
import com.example.inventorymanagementsystem.Models.Product;
import com.example.inventorymanagementsystem.Utility.UuidMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = UuidMapper.class)
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "category.categoryId", qualifiedByName = "uuidToString")
    ProductDTO toDTO(Product product);

    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "category", source = "category")
    Product toEntity(ProductDTO dto, com.example.inventorymanagementsystem.Models.Category category);
}

