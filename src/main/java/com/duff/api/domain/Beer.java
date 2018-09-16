package com.duff.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "beer")
public class Beer {

    @Id
    @Column(name = "style")
    private String style;

    @Column(name = "min_temperature")
    private double minTemperature;

    @Column(name = "max_temperature")
    private double maxTemperature;

    @JsonIgnore
    public double getAverage() {
        return (maxTemperature + minTemperature) / 2;
    }
}
