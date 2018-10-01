package com.ciclic.challenge.duff.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

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
}
