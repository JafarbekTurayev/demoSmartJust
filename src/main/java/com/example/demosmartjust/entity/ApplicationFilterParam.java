package com.example.demosmartjust.entity;

public class ApplicationFilterParam {

    private Long id;
    private String outDateStr;

    private String outNumber;

    private String inDateStr;

    private String inNumber;
    private ApplicationStatus status;
    private PageableParam pageable;

    public String getOutDateStr() {
        return outDateStr;
    }

    public void setOutDateStr(String outDateStr) {
        this.outDateStr = outDateStr;
    }

    public String getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(String outNumber) {
        this.outNumber = outNumber;
    }

    public String getInDateStr() {
        return inDateStr;
    }

    public void setInDateStr(String inDateStr) {
        this.inDateStr = inDateStr;
    }

    public String getInNumber() {
        return inNumber;
    }

    public void setInNumber(String inNumber) {
        this.inNumber = inNumber;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PageableParam getPageable() {
        return pageable;
    }

    public void setPageable(PageableParam pageable) {
        this.pageable = pageable;
    }
}
