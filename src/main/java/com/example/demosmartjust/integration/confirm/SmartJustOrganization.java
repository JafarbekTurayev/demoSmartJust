package com.example.demosmartjust.integration.confirm;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SmartJustOrganization  implements Serializable {
    private int id;
    private SmartJustName name;
    private String status;
    private SmartJustRegion region;
    @JsonProperty("sub_region")
    private SmartJustSubRegion subRegion;
    private SmartJustAddress address;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SmartJustRegion getRegion() {
        return region;
    }

    public void setRegion(SmartJustRegion region) {
        this.region = region;
    }

    public SmartJustSubRegion getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(SmartJustSubRegion subRegion) {
        this.subRegion = subRegion;
    }

    public SmartJustAddress getAddress() {
        return address;
    }

    public void setAddress(SmartJustAddress address) {
        this.address = address;
    }
}
