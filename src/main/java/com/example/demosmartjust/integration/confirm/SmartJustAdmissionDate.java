package com.example.demosmartjust.integration.confirm;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

public class SmartJustAdmissionDate  implements Serializable {
    private String weekDay;
    private String startTime;
    private String endTime;
    private int duration;
    private Map<String, Boolean> times;

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @JsonProperty("times")
    public Map<String, Boolean> getTimes() {
        return times;
    }

    public void setTimes(Map<String, Boolean> times) {
        this.times = times;
    }
}
