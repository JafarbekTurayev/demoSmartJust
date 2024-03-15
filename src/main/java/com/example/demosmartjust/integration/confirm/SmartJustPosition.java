package com.example.demosmartjust.integration.confirm;

import java.io.Serializable;

public class SmartJustPosition implements Serializable {
    private int id;
    private SmartJustName name;
    private String description;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SmartJustName getName() {
        return name;
    }

    public void setName(SmartJustName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
