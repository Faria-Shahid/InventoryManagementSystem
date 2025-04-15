package com.example.inventorymanagementsystem.Services.Store;

import com.example.inventorymanagementsystem.DTO.StoreGenerationRequestDTO;
import com.example.inventorymanagementsystem.Mapping.StoreMapper;
import com.example.inventorymanagementsystem.Models.Store;
import com.example.inventorymanagementsystem.Repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    public StoreServiceImpl(StoreRepository storeRepository, StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Optional<Store> getStoreFromId(String uuid) {
        UUID id = UUID.fromString(uuid);

        return storeRepository.findById(id);
    }

    @Override
    public boolean addStore(StoreGenerationRequestDTO storeGenerationRequestDTO) {
        Store store = storeMapper.toEntity(storeGenerationRequestDTO);
        storeRepository.save(store);

        Optional<Store> optionalStore = storeRepository.findStoreByStoreAddress(store.getStoreAddress());

        return optionalStore.isPresent();
    }

    @Override
    public boolean removeStore(String uuid) {
        UUID id = UUID.fromString(uuid);
        Optional<Store> optionalStore = storeRepository.findById(id);

        if (optionalStore.isPresent()) {
            storeRepository.delete(optionalStore.get());

            Optional<Store> check = storeRepository.findById(id);

            return check.isEmpty();
        }

        return false;
    }

    @Override
    public boolean updateStoreDetails(String uuid, StoreGenerationRequestDTO storeUpdateRequestDTO) {
        Optional<Store> storeOptional = getStoreFromId(uuid);

        if (storeOptional.isPresent()) {
            Store store = storeOptional.get();
            storeMapper.updateStoreFromDto(storeUpdateRequestDTO, store);
            storeRepository.save(store);
            return true;
        }

        return false;
    }

}
