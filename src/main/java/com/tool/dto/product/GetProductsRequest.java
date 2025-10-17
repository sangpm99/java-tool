package com.tool.dto.product;

import java.util.List;

public class GetProductsRequest {
    private String searchValue;

    private Integer pageSize;

    private List<Long> productCategoryIds;

    public String getSearchValue () {
        return searchValue;
    }

    public void setSearchValue (String searchValue) {
        this.searchValue = searchValue;
    }

    public Integer getPageSize () {
        return pageSize;
    }

    public void setPageSize (Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<Long> getProductCategoryIds () {
        return productCategoryIds;
    }

    public void setProductCategoryIds (List<Long> productCategoryIds) {
        this.productCategoryIds = productCategoryIds;
    }
}
