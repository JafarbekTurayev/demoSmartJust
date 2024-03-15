package com.example.demosmartjust.integration.confirm;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class SmartJustConfirmResponseData implements Serializable {
    private int id;
    private String pinfl;
    private String fio;
    @JsonProperty("birth_date")
    private Date birthDate;
    private String email;
    private String phone;
    private SmartJustAddress address;
    private String description;
    @JsonProperty("admission_date")
    private String admissionDate;
    @JsonProperty("admission_time")
    private String admissionTime;
    private SmartJustStaff staff;
    private SmartJustFile file;
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("application_status")
    private String applicationStatus;
    private String status;
    private String message;
    @JsonProperty("result_file")

    private SmartJustResultFile resultFile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPinfl() {
        return pinfl;
    }

    public void setPinfl(String pinfl) {
        this.pinfl = pinfl;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public SmartJustAddress getAddress() {
        return address;
    }

    public void setAddress(SmartJustAddress address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(String admissionTime) {
        this.admissionTime = admissionTime;
    }

    public SmartJustStaff getStaff() {
        return staff;
    }

    public void setStaff(SmartJustStaff staff) {
        this.staff = staff;
    }

    public SmartJustFile getFile() {
        return file;
    }

    public void setFile(SmartJustFile file) {
        this.file = file;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SmartJustResultFile getResultFile() {
        return resultFile;
    }

    public void setResultFile(SmartJustResultFile resultFile) {
        this.resultFile = resultFile;
    }
}
