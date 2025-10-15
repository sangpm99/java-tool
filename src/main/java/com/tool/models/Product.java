package com.tool.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Double price;

  @ManyToMany
  @JoinTable(
    name = "product_category_map",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private Set<ProductCategory> categories = new HashSet<>();

  // constructors, getters, setters
  public Product() {}
  public Product(String name, Double price) { this.name = name; this.price = price; }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public Double getPrice() { return price; }
  public void setPrice(Double price) { this.price = price; }
  public Set<ProductCategory> getCategories() { return categories; }
  public void setCategories(Set<ProductCategory> categories) { this.categories = categories; }
}
