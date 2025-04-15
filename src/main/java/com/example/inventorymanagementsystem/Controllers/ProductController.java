package com.example.inventorymanagementsystem.Controllers;

import com.example.inventorymanagementsystem.DTO.ProductDTO;
import com.example.inventorymanagementsystem.Models.Product;
import com.example.inventorymanagementsystem.Services.Product.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {

    final private ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping("/centralCatalogue")
    public List<Product> getAllProducts(){
        return productServiceImpl.getAllProducts();
    }

    @GetMapping("/centralCatalogue/{id}")
    public Product getProductDetails(@PathVariable UUID id){
        Product product = productServiceImpl.getProductById(id);

        if (product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        return product;
    }

    @PostMapping("/centralCatalogue")
    public ResponseEntity<String> addProductToCatalogue(@RequestBody ProductDTO productDTO) {
        if (productServiceImpl.addProduct(productDTO)) {
            return new ResponseEntity<>("Product added to Catalogue successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add product to Catalogue.", HttpStatus.BAD_REQUEST);
        }
    }

}
