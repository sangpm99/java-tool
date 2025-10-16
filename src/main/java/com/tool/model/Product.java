package com.tool.model;

import jakarta.persistence.*;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "product_product_category", // bảng mới, kết nối giữa product và product category
        joinColumns = @JoinColumn(name = "product_id"), // khóa tham chiếu
        inverseJoinColumns = @JoinColumn(name = "product_category_id") // khóa tham chiếu
    )
    @JsonManagedReference
    private Set<ProductCategory> likedProductCategories;

    // Getter & Setter
    public Product () {}

    public Product (String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductCategory> getLikedProductCategories() {
        return likedProductCategories;
    }

    public void setLikedProductCategories(Set<ProductCategory> likedProductCategories) {
        this.likedProductCategories = likedProductCategories;
    }
}
