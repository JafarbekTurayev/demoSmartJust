package com.example.demosmartjust.integration.confirm;

import java.io.Serializable;
import java.util.List;

public class SmartJustStaff implements Serializable {
    private int id;
    private SmartJustPosition position;
    private SmartJustOrganization organization;
    private SmartJustEmployee employee;
    private String status;
    private List<SmartJustAdmissionDate> admission_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SmartJustPosition getPosition() {
        return position;
    }

    public void setPosition(SmartJustPosition position) {
        this.position = position;
    }

    public SmartJustOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(SmartJustOrganization organization) {
        this.organization = organization;
    }

    public SmartJustEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(SmartJustEmployee employee) {
        this.employee = employee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SmartJustAdmissionDate> getAdmission_date() {
        return admission_date;
    }

    public void setAdmission_date(List<SmartJustAdmissionDate> admission_date) {
        this.admission_date = admission_date;
    }
}
