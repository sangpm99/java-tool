package com.tool.services;

import com.tool.models.Product;
import com.tool.models.ProductCategory;
import com.tool.repositories.ProductCategoryRepository;
import com.tool.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepo;
  private final ProductCategoryRepository categoryRepo;

  public ProductService(ProductRepository productRepo, ProductCategoryRepository categoryRepo) {
    this.productRepo = productRepo;
    this.categoryRepo = categoryRepo;
  }

  public Product createProduct(Product p, Set<Long> categoryIds) {
    if (categoryIds != null) {
      categoryIds.forEach(id -> categoryRepo.findById(id).ifPresent(p.getCategories()::add));
    }
    return productRepo.save(p);
  }

  public Product addCategoryToProduct(Long productId, Long categoryId) {
    Product prod = productRepo.findById(productId).orElseThrow();
    ProductCategory cat = categoryRepo.findById(categoryId).orElseThrow();
    prod.getCategories().add(cat);
    return productRepo.save(prod);
  }

  // các method khác: list, get, delete, update
}
