package com.tool.service;

import org.springframework.stereotype.Service;

import com.tool.model.ProductCategory;
import com.tool.repository.ProductCategoryRepository;

import jakarta.annotation.PostConstruct;

@Service
public class DatabaseInitializer {
    private final ProductCategoryRepository repo;

    public DatabaseInitializer(ProductCategoryRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void initDefaultCategory() {
        if (repo.count() == 0) {
            repo.save(new ProductCategory("Uncategory"));
        }
    }
}
