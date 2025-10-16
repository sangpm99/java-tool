package com.tool.dto;

import java.util.Set;

public class ProductRequest {
    private String name;
    private Set<Long> productCategoryIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getProductCategoryIds () {
        return productCategoryIds;
    }

    public void setProductCategoryIds (Set<Long> ids) {
        this.productCategoryIds = ids;
    }
}
