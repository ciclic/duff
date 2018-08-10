package com.gustavo.cervejaciclic;

import java.util.Objects;

public class Cerveja {
    private final String estilo;
    private final Integer minima;
    private final Integer maxima;

    public Cerveja(String estilo, Integer minima, Integer maxima) {
        this.estilo = estilo;
        this.minima = minima;
        this.maxima = maxima;
    }


    public String getEstilo() {
        return estilo;
    }

    public Integer getMinima() {
        return minima;
    }

    public Integer getMaxima() {
        return maxima;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cerveja cerveja = (Cerveja) o;
        return Objects.equals(estilo, cerveja.estilo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estilo);
    }
}
