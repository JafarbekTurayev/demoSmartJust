package com.example.demosmartjust.feign;


import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class FileStorageDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String extension;

    @NotNull
    private Long fileSize;

    @NotNull
    private String hashId;

    @NotNull
    private String contentType;

    @NotNull
    private String uploadPath;

//    @NotNull
//    private FileStorageType fileType;

//    @NotNull
//    private FileStorageStatus status;

    @NotNull
    private String actualStartDateStr;

    private String actualEndDateStr;

    private String createdDateStr;

    private Instant createdDate;

    private Long createdJobId;

    private Long createdEmployeeId;

    private String lastModifiedDateStr;

    private Instant lastModifiedDate;

    private Long lastModifiedJobId;

    private Long lastModifiedEmployeeId;

    private String originalHashId;

    private byte[] bytes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

//    public FileStorageType getFileType() {
//        return fileType;
//    }
//
//    public void setFileType(FileStorageType fileType) {
//        this.fileType = fileType;
//    }
//
//    public FileStorageStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(FileStorageStatus status) {
//        this.status = status;
//    }

    public String getActualStartDateStr() {
        return actualStartDateStr;
    }

    public void setActualStartDateStr(String actualStartDateStr) {
        this.actualStartDateStr = actualStartDateStr;
    }

    public String getActualEndDateStr() {
        return actualEndDateStr;
    }

    public void setActualEndDateStr(String actualEndDateStr) {
        this.actualEndDateStr = actualEndDateStr;
    }

    public String getCreatedDateStr() {
        return createdDateStr;
    }

    public void setCreatedDateStr(String createdDateStr) {
        this.createdDateStr = createdDateStr;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedJobId() {
        return createdJobId;
    }

    public void setCreatedJobId(Long createdJobId) {
        this.createdJobId = createdJobId;
    }

    public Long getCreatedEmployeeId() {
        return createdEmployeeId;
    }

    public void setCreatedEmployeeId(Long createdEmployeeId) {
        this.createdEmployeeId = createdEmployeeId;
    }

    public String getLastModifiedDateStr() {
        return lastModifiedDateStr;
    }

    public void setLastModifiedDateStr(String lastModifiedDateStr) {
        this.lastModifiedDateStr = lastModifiedDateStr;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getLastModifiedJobId() {
        return lastModifiedJobId;
    }

    public void setLastModifiedJobId(Long lastModifiedJobId) {
        this.lastModifiedJobId = lastModifiedJobId;
    }

    public Long getLastModifiedEmployeeId() {
        return lastModifiedEmployeeId;
    }

    public void setLastModifiedEmployeeId(Long lastModifiedEmployeeId) {
        this.lastModifiedEmployeeId = lastModifiedEmployeeId;
    }

    public String getOriginalHashId() {
        return originalHashId;
    }

    public void setOriginalHashId(String originalHashId) {
        this.originalHashId = originalHashId;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileStorageDTO)) {
            return false;
        }

        FileStorageDTO fileStorageDTO = (FileStorageDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, fileStorageDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "FileStorageDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", extension='" + extension + '\'' +
            ", fileSize=" + fileSize +
            ", hashId='" + hashId + '\'' +
            ", contentType='" + contentType + '\'' +
            ", uploadPath='" + uploadPath + '\'' +
            ", actualStartDateStr='" + actualStartDateStr + '\'' +
            ", actualEndDateStr='" + actualEndDateStr + '\'' +
            ", createdDateStr='" + createdDateStr + '\'' +
            ", createdDate=" + createdDate +
            ", createdJobId=" + createdJobId +
            ", createdEmployeeId=" + createdEmployeeId +
            ", lastModifiedDateStr='" + lastModifiedDateStr + '\'' +
            ", lastModifiedDate=" + lastModifiedDate +
            ", lastModifiedJobId=" + lastModifiedJobId +
            ", lastModifiedEmployeeId=" + lastModifiedEmployeeId +
            '}';
    }
}
