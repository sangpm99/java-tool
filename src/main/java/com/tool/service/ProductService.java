package com.tool.service;

import com.tool.model.Product;
import com.tool.model.ProductCategory;
import com.tool.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

import com.tool.repository.ProductCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    // get all
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    // get one
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // create
    public void createProduct (String name, Set<Long> productCategoryIds) {
        Product product = new Product(name);

        Set<ProductCategory> productCategories = new HashSet<>();
        for (Long categoryId : productCategoryIds) {
            Optional<ProductCategory> optionalCategory = productCategoryRepository.findById(categoryId);

            if (optionalCategory.isPresent()) {
                ProductCategory category = optionalCategory.get();
                productCategories.add(category);
            }
        }

        product.setLikedProductCategories(productCategories);
        productRepository.save(product);
    }

    // update
    public void updateProduct (Long id, String name, Set<Long> productCategoryIds) {
        Product existing = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        existing.setName(name);

        Set<ProductCategory> productCategories = new HashSet<>();
        for (Long categoryId : productCategoryIds) {
            Optional<ProductCategory> optionalCategory = productCategoryRepository.findById(categoryId);

            if (optionalCategory.isPresent()) {
                ProductCategory category = optionalCategory.get();
                productCategories.add(category);
            }
        }

        existing.setLikedProductCategories(productCategories);
        productRepository.save(existing);
    }

    // delete
    public void deleteProduct (Long id) {
        Product existing = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        productRepository.delete(existing);
    }
}
