package com.example.demosmartjust.integration.application;

import java.io.Serializable;

public class SmartJustSort  implements Serializable {
    private String name;
    private String direction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
