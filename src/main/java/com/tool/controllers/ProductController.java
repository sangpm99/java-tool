package com.tool.controllers;


import com.tool.models.Product;
import com.tool.models.ProductCategory;
import com.tool.repositories.ProductCategoryRepository;
import com.tool.repositories.ProductRepository;
import com.tool.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/Product")
public class ProductController {
  private final ProductRepository productRepo;
  private final ProductCategoryRepository categoryRepo;
  private final ProductService productService;

  public ProductController(ProductRepository productRepo, ProductCategoryRepository categoryRepo, ProductService productService) {
    this.productRepo = productRepo;
    this.categoryRepo = categoryRepo;
    this.productService = productService;
  }

  @GetMapping("/GetProducts")
  public List<Product> listProducts() { return productRepo.findAll(); }

  @GetMapping("/GetProduct/{id}")
  public ResponseEntity<Product> getProduct(@PathVariable Long id) {
    return productRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/CreateProduct")
  public ResponseEntity<Product> createProduct(@RequestBody ProductRequest req) {
    Product p = new Product(req.name, req.price);
    Product saved = productService.createProduct(p, req.categoryIds);
    return ResponseEntity.created(URI.create("/api/products/" + saved.getId())).body(saved);
  }

  @DeleteMapping("/DeleteProduct/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    if (!productRepo.existsById(id)) return ResponseEntity.notFound().build();
    productRepo.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/GetCategories")
  public List<ProductCategory> listCategories() { return categoryRepo.findAll(); }

  @PostMapping("/CreateCategory")
  public ResponseEntity<ProductCategory> createCategory(@RequestBody ProductCategory cat) {
    ProductCategory saved = categoryRepo.save(cat);
    return ResponseEntity.created(URI.create("/api/categories/" + saved.getId())).body(saved);
  }

  @PostMapping("/products/{productId}/categories/{categoryId}")
  public ResponseEntity<Product> addCategory(@PathVariable Long productId, @PathVariable Long categoryId) {
    Product updated = productService.addCategoryToProduct(productId, categoryId);
    return ResponseEntity.ok(updated);
  }

  // DTO request
  public static class ProductRequest {
    public String name;
    public Double price;
    public Set<Long> categoryIds;
  }
}
