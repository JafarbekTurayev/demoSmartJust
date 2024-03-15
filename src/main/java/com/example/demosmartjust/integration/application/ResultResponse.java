package com.example.demosmartjust.integration.application;

import java.io.Serializable;
import java.util.List;

public class ResultResponse implements Serializable {

    private boolean success;
    private List<AppResponseData> data;
    private SmartJustPage page;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<AppResponseData> getData() {
        return data;
    }

    public void setData(List<AppResponseData> data) {
        this.data = data;
    }

    public SmartJustPage getPage() {
        return page;
    }

    public void setPage(SmartJustPage page) {
        this.page = page;
    }
}
