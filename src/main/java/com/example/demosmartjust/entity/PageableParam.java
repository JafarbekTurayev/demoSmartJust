package com.example.demosmartjust.entity;


import org.springframework.data.domain.Sort;

public class PageableParam {

    private int pageIndex;

    private int pageSize;

    private String sort;

    private Sort.Direction direction;

    public PageableParam(int pageIndex, int pageSize, String sort) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }
}
