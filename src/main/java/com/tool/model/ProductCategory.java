package com.tool.model;

import jakarta.persistence.*;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "product_category")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "likedProductCategories") // không cần khai báo JoinTable nữa vì khai báo bên Product rồi, chỉ cần map
    @JsonBackReference
    private Set<Product> likes;

    // Getter & Setter
    public ProductCategory() {}
    
    public ProductCategory(String name) {
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

    public Set<Product> getLikes() {
        return likes;
    }

    public void setLikes(Set<Product> likes) {
        this.likes = likes;
    }
}
