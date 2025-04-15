package com.example.inventorymanagementsystem.Services.Category;

import com.example.inventorymanagementsystem.DTO.CategoryRequestDTO;
import com.example.inventorymanagementsystem.Models.Category;
import com.example.inventorymanagementsystem.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Optional<UUID> getCategoryIdByCategoryName(String categoryName) {
        Optional<Category> foundCategory = categoryRepository.findCategoryByCategoryName(categoryName.toLowerCase());

        if (foundCategory.isPresent()) {
            UUID categoryId = foundCategory.get().getCategoryId();
            return Optional.of(categoryId);
        } else {
            return Optional.empty();
        }
    }

    public boolean addCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category();

        category.setCategoryName(categoryRequestDTO.getCategoryName().toLowerCase());

        Optional<UUID> id = getCategoryIdByCategoryName(categoryRequestDTO.getCategoryName());

        if (id.isEmpty()) {
            Category savedCategory = categoryRepository.save(category);

            return savedCategory.getCategoryId() != null;
        }

        return false;
    }

    public Category getCategoryById(String uuid){
        UUID id = UUID.fromString(uuid);

        Optional<Category> category = categoryRepository.findById(id);

        return category.orElse(null);
    }


}
