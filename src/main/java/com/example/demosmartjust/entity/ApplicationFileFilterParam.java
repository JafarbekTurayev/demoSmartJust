package com.example.demosmartjust.entity;


public class ApplicationFileFilterParam {

    private Long id;

    private String fileStorageHashId;

    private Long applicationId;

    private PageableParam pageable;

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

    public PageableParam getPageable() {
        return pageable;
    }

    public void setPageable(PageableParam pageable) {
        this.pageable = pageable;
    }
}
