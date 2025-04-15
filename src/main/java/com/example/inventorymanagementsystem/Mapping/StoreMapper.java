package com.example.inventorymanagementsystem.Mapping;

import com.example.inventorymanagementsystem.DTO.StoreGenerationRequestDTO;
import com.example.inventorymanagementsystem.Models.Store;
import com.example.inventorymanagementsystem.Utility.UuidMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = UuidMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StoreMapper {
    @Mapping(target = "storeId", ignore = true)
    Store toEntity(StoreGenerationRequestDTO dto);

    void updateStoreFromDto(StoreGenerationRequestDTO dto, @MappingTarget Store store);


}
