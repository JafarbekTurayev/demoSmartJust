package com.example.demosmartjust.integration.application;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class SmartJustPageableRequest implements Serializable {
    @JsonProperty("per_page")
    private int perPage;
    private int page;
    private SmartJustSort sort;
    private List<SmartJustSearch> search;

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public SmartJustSort getSort() {
        return sort;
    }

    public void setSort(SmartJustSort sort) {
        this.sort = sort;
    }

    public List<SmartJustSearch> getSearch() {
        return search;
    }

    public void setSearch(List<SmartJustSearch> search) {
        this.search = search;
    }
}
