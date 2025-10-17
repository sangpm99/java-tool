package com.tool.service;

import com.tool.model.Product;
import com.tool.model.ProductCategory;
import com.tool.repository.ProductCategoryRepository;
import com.tool.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductRepository productRepository;
    
    // get All
    public List<ProductCategory> getProductCategories() {
        return productCategoryRepository.findAll();
    }
    
    // get
    public ProductCategory getCategoryById(Long id) {
        return productCategoryRepository.findById(id).orElse(null);
    }

    // create
    public void createCategory(String name) {
        String normalized = name == null ? "" : name.trim();

        if (normalized.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name cannot be empty.");
        }

        if (productCategoryRepository.existsByNameIgnoreCaseTrimmed(normalized)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name already exists: " + normalized);
        }

        ProductCategory productCategory = new ProductCategory(normalized);
        productCategoryRepository.save(productCategory);
    }

    // update
    public void updateCategory(Long id, String name) {
        String normalized = name == null ? "" : name.trim();

        if (normalized.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name cannot be empty.");
        }

        ProductCategory existing = productCategoryRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id: " + id));

        if (!existing.getName().trim().equalsIgnoreCase(normalized)
                && productCategoryRepository.existsByNameIgnoreCaseTrimmed(normalized)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name already exists: " + normalized);
        }

        existing.setName(normalized);
        productCategoryRepository.save(existing);
    }
    
    // delete
    public void deleteCategory(Long id) {
        if (id == 1) {
            throw new IllegalArgumentException("Không thể xóa category mặc định 'Uncategory'.");
        }

        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy category với id: " + id));

        ProductCategory uncategory = productCategoryRepository.findById(1L)
                .orElseThrow(() -> new IllegalStateException("Category mặc định 'Uncategory' (id=1) không tồn tại."));

        // 1. Lấy tất cả sản phẩm có liên kết với category này
        List<Product> products = productRepository.findProductsByCategoryId(id);

        // 2. Cập nhật lại mối quan hệ
        for (Product p : products) {
            Set<ProductCategory> newCategories = new HashSet<>(p.getProductCategories());
            newCategories.removeIf(c -> c.getId().equals(id)); // xóa category cần xóa
            newCategories.add(uncategory); // thêm Uncategory
            p.setProductCategories(newCategories);
        }

        // 3. Lưu lại toàn bộ sản phẩm
        productRepository.saveAll(products);

        // 4. Xóa category
        productCategoryRepository.delete(category);
    }
}
