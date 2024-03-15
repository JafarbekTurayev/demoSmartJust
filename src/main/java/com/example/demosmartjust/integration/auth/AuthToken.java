package com.example.demosmartjust.integration.auth;

import java.io.Serializable;

public class AuthToken implements Serializable {
    private boolean success;
    private String data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
