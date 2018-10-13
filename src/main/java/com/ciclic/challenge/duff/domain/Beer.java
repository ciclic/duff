package com.ciclic.challenge.duff.domain;

import javax.persistence.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String style;

    @NotNull
    private int minTemperature;

    @NotNull
    private int maxTemperature;

    public Beer(){

    }

    public Beer(Long id, String style, int minTemperature, int maxTemperature) {
        this.id = id;
        this.style = style;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(int maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getStyle(){
        return style;
    }

    public Double getTemp(){
        return (minTemperature + maxTemperature) /2.0;
    }
}

