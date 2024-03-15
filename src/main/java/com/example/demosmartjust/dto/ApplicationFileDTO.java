package com.example.demosmartjust.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.example.demosmartjust.entity.ApplicationFile} entity.
 */
public class ApplicationFileDTO implements Serializable {

    private Long id;

    @NotNull
    private String fileStorageHashId;

    private Long applicationId;

    private String name;

    private Long fileSize;

    private String extension;

    private String hashId;
    private String smartJustFileId;
    private String fileType;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getSmartJustFileId() {
        return smartJustFileId;
    }

    public void setSmartJustFileId(String smartJustFileId) {
        this.smartJustFileId = smartJustFileId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileStorageHashId() {
        return fileStorageHashId;
    }

    public void setFileStorageHashId(String fileStorageHashId) {
        this.fileStorageHashId = fileStorageHashId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationFileDTO)) {
            return false;
        }

        ApplicationFileDTO applicationFileDTO = (ApplicationFileDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, applicationFileDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
