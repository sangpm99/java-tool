package com.tool.dto.product;

import java.util.List;

import com.tool.dto.ProductRequest;

public class CreateProductsRequest {
    private List<ProductRequest> products;

    public List<ProductRequest> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRequest> products) {
        this.products = products;
    }
}
