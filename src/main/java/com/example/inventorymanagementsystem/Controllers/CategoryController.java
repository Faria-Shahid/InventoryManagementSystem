package com.example.inventorymanagementsystem.Controllers;

import com.example.inventorymanagementsystem.DTO.CategoryRequestDTO;
import com.example.inventorymanagementsystem.Models.Category;
import com.example.inventorymanagementsystem.Services.Category.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CategoryController {
    private final CategoryServiceImpl categoryServiceImpl;

    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories() {
        List<Category> categories = categoryServiceImpl.getAllCategory();

        if (categories.isEmpty()) {
            return ResponseEntity
                    .ok("No categories added yet.");
        } else {
            return ResponseEntity
                    .ok(categories);
        }
    }

    @PostMapping("/category")
    public ResponseEntity<String> addCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        boolean check = categoryServiceImpl.addCategory(categoryRequestDTO);

        if (check) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Category '" + categoryRequestDTO.getCategoryName()+ "' added successfully.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Failed to add category '" + categoryRequestDTO.getCategoryName() + "'. It may already exist or something went wrong.");
        }
    }

    @GetMapping("/category/categoryName")
    public ResponseEntity<?> getCategoryId(@Valid @RequestParam String categoryName){
        Optional<UUID> uuidOptional = categoryServiceImpl.getCategoryIdByCategoryName(categoryName);

        if (uuidOptional.isPresent()){
            return ResponseEntity.ok(uuidOptional.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
    }
}
