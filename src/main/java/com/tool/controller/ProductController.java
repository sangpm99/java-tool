package com.tool.controller;

import com.tool.dto.product.GetProductsRequest;
import com.tool.dto.ProductRequest;
import com.tool.model.Product;
import com.tool.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Product")
public class ProductController {
  @Autowired
  private ProductService productService;

  @GetMapping("/GetProducts")
  public List<Product> getProducts(
    @RequestParam(required = false) String searchValue
  ) {
    return this.productService.getProducts(searchValue);
  }

  @GetMapping("/GetProduct/{id}")
  public Product getProducts(@PathVariable Long id) {
    return this.productService.findById(id);
  }

  @PostMapping("/CreateProduct")
  public void createProduct(@RequestBody ProductRequest body) {
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
