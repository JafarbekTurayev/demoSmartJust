package com.example.demosmartjust.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "application")
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APPLICATION_ID")
    @SequenceGenerator(name = "SEQ_APPLICATION_ID", sequenceName = "SEQ_APPLICATION_ID", allocationSize = 1, initialValue = 1)
    private Long id;

    private String outDateStr;

    private String outNumber;

    private String inDateStr;

    private String inNumber;
    private String fio;
    private Date birthDate;
    private String phone;
    private String pinfl;
    private int organizationId;
    @Column(columnDefinition = "text")
    private String address;
    private String description;
    private String email;
    private Date admissionDate;
    private LocalTime admissionTime;

    @Enumerated(value = EnumType.STRING)
    private ApplicationStatus applicationStatus;
    private String fileId;
    private String resultFileId;

    private String organization;
    private String position;
    @Column(name = "employee_pinfl")
    private String employeePinfl;
    @Column(name = "employee_fio")
    private String employeeFio;

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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmployeePinfl() {
        return employeePinfl;
    }

    public void setEmployeePinfl(String employeePinfl) {
        this.employeePinfl = employeePinfl;
    }

    public String getEmployeeFio() {
        return employeeFio;
    }

    public void setEmployeeFio(String employeeFio) {
        this.employeeFio = employeeFio;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
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
