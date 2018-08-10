package com.gustavo.cervejaciclic;

public class Cerveja {
    private final String estilo;
    private final Integer minima;
    private final Integer maxima;

    public Cerveja(String estilo, Integer minima, Integer maxima) {
        this.estilo = estilo;
        this.minima = minima;
        this.maxima = maxima;
    }

    public Integer getMinima() {
        return minima;
    }

    public Integer getMaxima() {
        return maxima;
    }

    public String getEstilo() {
        return estilo;
    }
}
