package com.example.inventorymanagementsystem.Repository;

import com.example.inventorymanagementsystem.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findCategoryByCategoryName(String categoryName);
}
