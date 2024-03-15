package com.example.demosmartjust.integration.confirm;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SmartJustConfirmRequest  implements Serializable {
    private String status;
    @JsonProperty("file_id")
    private int fileId;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
