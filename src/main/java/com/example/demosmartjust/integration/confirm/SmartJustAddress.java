package com.example.demosmartjust.integration.confirm;

import java.io.Serializable;

public class SmartJustAddress  implements Serializable {
    private String uz;
    private String ru;
    private String en;

    public String getUz() {
        return uz;
    }

    public void setUz(String uz) {
        this.uz = uz;
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }
}
