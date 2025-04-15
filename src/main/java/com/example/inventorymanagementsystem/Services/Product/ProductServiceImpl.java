package com.example.inventorymanagementsystem.Services.Product;

import com.example.inventorymanagementsystem.DTO.ProductDTO;
import com.example.inventorymanagementsystem.Mapping.ProductMapper;
import com.example.inventorymanagementsystem.Models.Category;
import com.example.inventorymanagementsystem.Models.Product;
import com.example.inventorymanagementsystem.Repository.ProductRepository;
import com.example.inventorymanagementsystem.Services.Category.CategoryServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryServiceImpl categoryServiceImpl;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryServiceImpl categoryServiceImpl, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryServiceImpl = categoryServiceImpl;
        this.productMapper = productMapper;
    }

    public Product getProductById(UUID productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public boolean addProduct(ProductDTO productDTO) {
        try {
            Category category = categoryServiceImpl.getCategoryById(productDTO.getCategoryId());

            if (category != null) {
                Product product = productMapper.toEntity(productDTO, category);
                productRepository.save(product);

                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
