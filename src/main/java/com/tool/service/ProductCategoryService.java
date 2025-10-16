package com.tool.service;

import com.tool.model.ProductCategory;
import com.tool.repository.ProductCategoryRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    
    // get All
    public List<ProductCategory> getProductCategories() {
        return productCategoryRepository.findAll();
    }
    
    // get
    public ProductCategory getCategoryById(Long id) {
        return productCategoryRepository.findById(id).orElse(null);
    }

    // create
    public void createCategory (String name) {
        ProductCategory productCategory = new ProductCategory(name);
        productCategoryRepository.save(productCategory);
    }

    // update
    public void updateCategory(Long id, String name) {
        ProductCategory existing = productCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
        existing.setName(name);
        productCategoryRepository.save(existing);
    }
    
    // delete
    public void deleteCategory(Long id) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        productCategoryRepository.delete(category);
    }
}
