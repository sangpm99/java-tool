package com.tool.dto.product;

import java.util.Set;

public class GetProductsRequest {
    private String searchValue;

    private Integer pageIndex;

    private Integer pageSize;

    private Set<Long> productCategoryIds;
}
