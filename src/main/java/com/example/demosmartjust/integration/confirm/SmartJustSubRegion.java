package com.example.demosmartjust.integration.confirm;

import java.io.Serializable;

public class SmartJustSubRegion  implements Serializable {
    private int id;
    private SmartJustName name;

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
}
