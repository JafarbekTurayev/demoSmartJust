package com.example.demosmartjust.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicationConfirmRequestDTO {
    private String fileStorageHashId;
    private String status;
    @JsonProperty("file_id")
    private int fileId;
    private String message;

    public String getFileStorageHashId() {
        return fileStorageHashId;
    }

    public void setFileStorageHashId(String fileStorageHashId) {
        this.fileStorageHashId = fileStorageHashId;
    }

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
