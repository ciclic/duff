package com.gustavo.cervejaciclic.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@Entity
public class Cerveja {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estilo;

    @NotNull
    private Integer minima;

    @NotNull
    private Integer maxima;

    private String userId;

    private String playlistId;

    public Cerveja(@NotNull String estilo, @NotNull Integer minima, @NotNull Integer maxima) {
        this.estilo = estilo;
        this.minima = minima;
        this.maxima = maxima;
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
