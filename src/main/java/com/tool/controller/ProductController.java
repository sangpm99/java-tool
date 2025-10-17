package com.tool.controller;

import com.tool.dto.product.GetProductsRequest;
import com.tool.dto.product.CreateProductsRequest;
import com.tool.dto.PagedResponse;
import com.tool.dto.ProductRequest;
import com.tool.model.Product;
import com.tool.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Product")
public class ProductController {
  @Autowired
  private ProductService productService;

  @GetMapping("/GetProducts")
  public PagedResponse<Product> getProducts(GetProductsRequest body) {
    return this.productService.getProducts(body);
  }

  @GetMapping("/GetProduct/{id}")
  public Product getProducts(@PathVariable Long id) {
    return this.productService.findById(id);
  }

  @PostMapping("/CreateProduct")
  public void createProduct(@RequestBody CreateProductsRequest body) {
    this.productService.createProduct(body);
  }

  @PutMapping("/UpdateProduct/{id}")
  public void updateProduct(@PathVariable Long id, @RequestBody ProductRequest body) {
    this.productService.updateProduct(id, body);
  }

  @DeleteMapping("/DeleteProduct/{id}")
  public void deleteProduct(@PathVariable Long id) {
    this.productService.deleteProduct(id);
  }
}
