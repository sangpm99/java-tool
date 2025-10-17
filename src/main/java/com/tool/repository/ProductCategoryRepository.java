package com.tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tool.model.ProductCategory;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    @Query("SELECT COUNT(pc) > 0 FROM ProductCategory pc WHERE LOWER(TRIM(pc.name)) = LOWER(TRIM(:name))")
    boolean existsByNameIgnoreCaseTrimmed(@Param("name") String name);
}
