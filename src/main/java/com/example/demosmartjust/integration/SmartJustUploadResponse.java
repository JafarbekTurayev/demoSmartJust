package com.example.demosmartjust.integration;


import com.example.demosmartjust.integration.confirm.SmartJustFile;

public class SmartJustUploadResponse {
    private boolean success;
    private SmartJustFile data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public SmartJustFile getData() {
        return data;
    }

    public void setData(SmartJustFile data) {
        this.data = data;
    }
}
