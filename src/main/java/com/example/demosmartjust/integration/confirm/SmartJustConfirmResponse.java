package com.example.demosmartjust.integration.confirm;

import java.io.Serializable;

public class SmartJustConfirmResponse  implements Serializable {
    private int status;
    private String message;
    private SmartJustConfirmResponseData data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SmartJustConfirmResponseData getData() {
        return data;
    }

    public void setData(SmartJustConfirmResponseData data) {
        this.data = data;
    }
}
