package com.example.demosmartjust.integration.application;


import com.example.demosmartjust.integration.confirm.SmartJustStaff;

import java.io.Serializable;

public class AppResponseData  implements Serializable {
    private int id;
    private String fio;
    private String description;
    private String admission_date;
    private String admission_time;
    private SmartJustStaff staff;
    private String application_status;
    private Object status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdmission_date() {
        return admission_date;
    }

    public void setAdmission_date(String admission_date) {
        this.admission_date = admission_date;
    }

    public String getAdmission_time() {
        return admission_time;
    }

    public void setAdmission_time(String admission_time) {
        this.admission_time = admission_time;
    }

    public SmartJustStaff getStaff() {
        return staff;
    }

    public void setStaff(SmartJustStaff staff) {
        this.staff = staff;
    }

    public String getApplication_status() {
        return application_status;
    }

    public void setApplication_status(String application_status) {
        this.application_status = application_status;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }
}
