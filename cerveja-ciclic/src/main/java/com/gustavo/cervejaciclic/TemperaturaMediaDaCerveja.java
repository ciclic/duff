package com.gustavo.cervejaciclic;

public class TemperaturaMediaDaCerveja {
    private final String estilo;
    private final int temperaturaMedia;

    public TemperaturaMediaDaCerveja(String estilo, int temperaturaMedia) {
        this.estilo = estilo;
        this.temperaturaMedia = temperaturaMedia;
    }


    public String getEstilo() {
        return estilo;
    }

    public int getTemperaturaMedia() {
        return temperaturaMedia;
    }
}
