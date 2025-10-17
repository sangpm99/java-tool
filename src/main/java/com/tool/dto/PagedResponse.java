package com.tool.dto;

import java.util.List;

public class PagedResponse<T> {
    private int pageSize;
    private long itemsCount;
    private List<T> items;
    private Object extra;

    public PagedResponse() {}

    public PagedResponse(int pageSize, long itemsCount, List<T> items, Object extra) {
        this.pageSize = pageSize;
        this.itemsCount = itemsCount;
        this.items = items;
        this.extra = extra;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(long itemsCount) {
        this.itemsCount = itemsCount;
    }

    public List<T> getItems () {
        return items;
    }

    public void setItems (List<T> items) {
        this.items = items;
    }

    public Object getExtra () {
        return extra;
    }

    public void setExtra (Object extra) {
        this.extra = extra;
    }
}
