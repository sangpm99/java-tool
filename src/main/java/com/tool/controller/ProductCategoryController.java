package com.tool.controller;

import com.tool.dto.ProductCategoryRequest;
import com.tool.model.ProductCategory;
import com.tool.service.ProductCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ProductCategory")
public class ProductCategoryController {
  @Autowired
  private ProductCategoryService productCategoryService;

  @GetMapping("/GetProductCategories")
  public List<ProductCategory> getProductCategories () {
    return this.productCategoryService.getProductCategories();
  }

  @GetMapping("/GetProductCategory/{id}")
  public ProductCategory getProductCategory (@PathVariable Long id) {
    return this.productCategoryService.getCategoryById(id);
  }

  @PostMapping("/CreateProductCategory")
  public void createProductCategory (@RequestBody ProductCategoryRequest body) {
    String name = body.getName();
    productCategoryService.createCategory(name);
  }

  @PutMapping("/UpdateProductCategory/{id}")
  public void updateCategory (@PathVariable Long id, @RequestBody ProductCategoryRequest body) {
    String name = body.getName();
    productCategoryService.updateCategory(id, name);
  }

  @DeleteMapping("/DeleteProductCategory/{id}")
  public void deleteProductCategory (@PathVariable Long id) {
    productCategoryService.deleteCategory(id);
  }
}
