package com.tool.controller;

import com.tool.dto.ProductRequest;
import com.tool.model.Product;
import com.tool.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/Product")
public class ProductController {
  @Autowired
  private ProductService productService;

  @GetMapping("/GetProducts")
  public List<Product> getProducts() {
    return this.productService.getProducts();
  }

  @GetMapping("/GetProduct/{id}")
  public Product getProducts(@PathVariable Long id) {
    return this.productService.findById(id);
  }

  @PostMapping("/CreateProduct")
  public void createProduct(@RequestBody ProductRequest body) {
    String name = body.getName();
    Set<Long> productCategoryIds = body.getProductCategoryIds();
    this.productService.createProduct(name, productCategoryIds);
  }

  @PutMapping("/UpdateProduct/{id}")
  public void updateProduct(@PathVariable Long id, @RequestBody ProductRequest body) {
    String name = body.getName();
    Set<Long> productCategoryIds = body.getProductCategoryIds();
    this.productService.updateProduct(id, name, productCategoryIds);
  }

  @DeleteMapping("/DeleteProduct/{id}")
  public void deleteProduct(@PathVariable Long id) {
    this.productService.deleteProduct(id);
  }
}
