package com.tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tool.model.Product;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    // Lấy random sản phẩm khi không có categoryIds
    @Query(
        value = """
            SELECT DISTINCT p.* 
            FROM product p
            WHERE p.type IN ('simple','variable')
            AND (:search IS NULL OR LOWER(p.name) LIKE CONCAT('%', LOWER(:search), '%'))
            ORDER BY RAND()
            LIMIT :limit
        """,
        nativeQuery = true
    )
    List<Product> findRandomProductsWithoutCategory(
        @Param("search") String searchValue,
        @Param("limit") int limit
    );

    // Lấy random sản phẩm khi có categoryIds
    @Query(
        value = """
            SELECT DISTINCT p.* 
            FROM product p
            LEFT JOIN product_product_category ppc 
                ON p.id = ppc.product_id
            WHERE p.type IN ('simple','variable')
            AND (:search IS NULL OR LOWER(p.name) LIKE CONCAT('%', LOWER(:search), '%'))
            AND (ppc.product_category_id IN (:categories))
            ORDER BY RAND()
            LIMIT :limit
        """,
        nativeQuery = true
    )
    List<Product> findRandomProductsByCategories(
        @Param("search") String searchValue,
        @Param("categories") List<Long> categoryIds,
        @Param("limit") int limit
    );

    // đếm số lượng product
    @Query(
        value = "SELECT COUNT(*) FROM product WHERE type IN ('simple', 'variable')",
        nativeQuery = true
    )
    long countSimpleAndVariableProducts();

    // Lấy cả product có categoryid (xóa category)
    @Query(value = """
        SELECT p.* 
        FROM product p
        JOIN product_product_category ppc ON p.id = ppc.product_id
        WHERE ppc.product_category_id = :categoryId
    """, nativeQuery = true)
    List<Product> findProductsByCategoryId(@Param("categoryId") Long categoryId);

    // Xóa những variation liên quan đến variable cần xóa
    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.type = 'variation' AND p.parent = :parentValue")
    void deleteVariationsByParent(@Param("parentValue") String parentValue);
}
