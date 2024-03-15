package com.example.demosmartjust.dto;

import com.example.demosmartjust.integration.confirm.SmartJustName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;


public class ApplicationDTO {
    private Long id;
    private String outDateStr;

    private String outNumber;

    private String inDateStr;

    private String inNumber;
    private String fio;
    private String firstName;
    private String lastName;
    private String middleName;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date birthDate;
    private String phone;
    private String pinfl;
    private int organizationId;
    private SmartJustName address;
    private String description;
    private String email;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date admissionDate;
    private LocalTime admissionTime;
    private String applicationStatus;
    private String fileId;
    private String resultFileId;
    private List<ApplicationFileDTO> applicationFileDTOList;

    public List<ApplicationFileDTO> getApplicationFileDTOList() {
        return applicationFileDTOList;
    }

    public void setApplicationFileDTOList(List<ApplicationFileDTO> applicationFileDTOList) {
        this.applicationFileDTOList = applicationFileDTOList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPinfl() {
        return pinfl;
    }

    public void setPinfl(String pinfl) {
        this.pinfl = pinfl;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public SmartJustName getAddress() {
        return address;
    }

    public void setAddress(SmartJustName address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalTime getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(LocalTime admissionTime) {
        this.admissionTime = admissionTime;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getResultFileId() {
        return resultFileId;
    }

    public void setResultFileId(String resultFileId) {
        this.resultFileId = resultFileId;
    }
}
